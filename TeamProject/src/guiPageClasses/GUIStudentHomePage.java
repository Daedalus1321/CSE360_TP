package guiPageClasses;

import applicationMainMethodClasses.FCMainClass;
import crud.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import databaseClasses.Database;
import entityClasses.User;

/*******
 * <p> Title: GUIReviewerHomePage Class. </p>
 * 
 * <p> Description: The Java/FX-based Single Role Home Page.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2025-04-20 Initial version
 *  
 */

public class GUIStudentHomePage {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the application values required by the user interface
	private Label label_PageTitle = new Label();
	private Label label_UserDetails = new Label();
	private Button button_UpdateThisUser = new Button("Account Update");
	
	private Line line_Separator1 = new Line(20, 130, FCMainClass.WINDOW_WIDTH-20, 130);
	
	private Line line_Separator4 = new Line(20, 525, FCMainClass.WINDOW_WIDTH-20,525);
	
	private Button button_Logout = new Button("Logout");
	private Button button_Quit = new Button("Quit");
	private Button button_Post = new Button("Post");
	
	private Stage primaryStage;	
	private Pane theRootPane;
	private Database theDatabase;
	private User theUser;
	
	private TableView<GenericQuestion> tblPosts = new TableView<>();
	private TableView<GenericAnswer> tblReplies = new TableView<>();
	
	private QuestionList CSE360 = new QuestionList("CSE360");
	private AnswerList CSE360_a = new AnswerList("");
	
	private TextField text_post = new TextField();
	private TextField text_keywords = new TextField();
	private TextField text_title = new TextField();
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/
	
	
	
	/**********
	 * <p> Method: GUIStudentHomePage(Stage ps, Pane theRoot, Database database, User user) </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object. </p>
	 * 
	 * @param ps specifies the JavaFX Stage to be used for this GUI and it's methods
	 * 
	 * @param theRoot specifies the JavaFX Pane to be used for this GUI and it's methods
	 * 
	 * @param database specifies the Database to be used by this GUI and it's methods
	 * 
	 * @param user specifies the User for this GUI and it's methods
	 * 
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public GUIStudentHomePage(Stage ps, Pane theRoot, Database database, User user) {
		GUISystemStartUpPage.theStudentHomePage = this;
		
		FCMainClass.activeHomePage = 2;
		
		primaryStage = ps;
		theRootPane = theRoot;
		theDatabase = database;
		theUser = user;
		
		double WINDOW_WIDTH = FCMainClass.WINDOW_WIDTH;
		double WINDOW_HEIGHT = FCMainClass.WINDOW_HEIGHT;

		primaryStage.setTitle("CSE 360 Foundation Code: User Home Page");

		// Label the window with the title and other common titles and buttons
		
		label_PageTitle.setText("Student Home Page");
		setupLabelUI(label_PageTitle, "Arial", 28, WINDOW_WIDTH, Pos.CENTER, 0, 5);

		label_UserDetails.setText("User: " + user.getUserName());
		setupLabelUI(label_UserDetails, "Arial", 20, WINDOW_WIDTH, Pos.BASELINE_LEFT, 20, 55);
		
		setupButtonUI(button_UpdateThisUser, "Dialog", 18, 160, Pos.BASELINE_LEFT, 20, 85);
		button_UpdateThisUser.setOnAction((event) -> {performUpdate(); });
		
        setupButtonUI(button_Logout, "Dialog", 18, 250, Pos.CENTER, 20, 540);
        button_Logout.setOnAction((event) -> {performLogout(); });
        
        setupButtonUI(button_Quit, "Dialog", 18, 250, Pos.CENTER, 300, 540);
        button_Quit.setOnAction((event) -> {performQuit(); });
        
        setupButtonUI(button_Post, "Dialog", 18, 50, Pos.BASELINE_LEFT, 20, 150);
        button_Post.setOnAction((event) -> {post(text_title.getText(), text_post.getText()); updateQuestions(); });
        
        text_title.setText("title");
        setupTextUI(text_title, "Arial", 18, 100, Pos.BASELINE_LEFT, 100, 150, true);
        text_post.setText("post content");
        setupTextUI(text_post, "Arial", 18, 300, Pos.BASELINE_LEFT, 210, 150, true);
        text_keywords.setText("keywords");
        setupTextUI(text_keywords, "Arial", 18, 250, Pos.BASELINE_LEFT, 520, 150, true);
        
        TableColumn<GenericQuestion, Boolean> colAnswered = new TableColumn<GenericQuestion, Boolean>("HasANS");
        TableColumn<GenericQuestion, String> colTitle = new TableColumn<GenericQuestion, String>("Title");
        TableColumn<GenericQuestion, String> colContent = new TableColumn<GenericQuestion, String>("Content");
        TableColumn<GenericQuestion, String> colPoster = new TableColumn<GenericQuestion, String>("User");
    	TableColumn<GenericQuestion, Integer> colReplies = new TableColumn<GenericQuestion, Integer>("# Replies");
    	TableColumn<GenericQuestion, String> colQID = new TableColumn<GenericQuestion, String>("QID");
        
    	colAnswered.setCellValueFactory( cellData -> new SimpleObjectProperty<Boolean>((CSE360.getQuestion(cellData.getValue().getQID()).getNumReplies() <= 0) ? false : (CSE360.getQuestion(cellData.getValue().getQID()).getReply(0).getRID().substring(0,1).equals("*"))));
    	colTitle.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
    	colContent.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getContent()));
    	colPoster.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getPoster()));
    	colReplies.setCellValueFactory( cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getNumreplies()));
    	colQID.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getQID()));
    	
        tblPosts.getColumns().addAll(colAnswered, colTitle, colContent, colPoster, colReplies, colQID);
        tblPosts.setLayoutX(10);
        tblPosts.setLayoutY(200);
        tblPosts.setMinSize(WINDOW_WIDTH-20, 150);
        tblPosts.setMaxHeight(150);
        
        tblPosts.resizeColumn(colAnswered, 10);
        tblPosts.resizeColumn(colTitle, 80);
        tblPosts.resizeColumn(colContent, 250);
        tblPosts.resizeColumn(colPoster, 40);
        tblPosts.resizeColumn(colReplies, 20);
        tblPosts.resizeColumn(colQID, 20);
        
        tblPosts.setOnMouseClicked((event) -> updateReplies(tblPosts.getSelectionModel().getSelectedItem()));
        
        TableColumn<GenericAnswer, Boolean> colMarked = new TableColumn<>("ANS");
        TableColumn<GenericAnswer, String> colRContent = new TableColumn<>("Response");
        TableColumn<GenericAnswer, String> colUser = new TableColumn<>("User");
        TableColumn<GenericAnswer, String> colRID = new TableColumn<>("RID");
        
        colMarked.setCellValueFactory( cellData -> new SimpleObjectProperty<Boolean>(cellData.getValue().getMarkedAnswer()));
        colRContent.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getContent()));
        colUser.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getPoster()));
        colRID.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getRID()));
        
        GenericQuestion selected = tblPosts.getSelectionModel().getSelectedItem();
        
        tblReplies.getColumns().addAll(colMarked, colRContent, colUser, colRID);
        tblReplies.setLayoutX(10);
        tblReplies.setLayoutY(360);
        tblReplies.setMinSize(WINDOW_WIDTH-20, 150);
        tblReplies.setMaxHeight(150);
        
        tblReplies.resizeColumn(colMarked, 20);
        tblReplies.resizeColumn(colRContent, 350);
        tblReplies.resizeColumn(colUser, 50);
        tblReplies.resizeColumn(colRID, 50);
        
        CSE360.linkReplies(CSE360_a);
       
        User u1 = new User("dSmit");
		User u2 = new User("oVoge");
		User u3 = new User("aHaus");
		User u4 = new User("mPros");
		User u5 = new User("pWord");
		User u6 = new User("jKale");
		
		
		String p1 = CSE360.post("Help with Gradle","content",u1);
			CSE360.reply(p1, "IDK dude", new User("Anon"));
		String p2 = CSE360.post("Merge Issues","content",u2);
			CSE360.reply(p2, "Try force", new User("Unwise Adam"));
			String r1 = CSE360.reply(p2, "Ask your teammates about specifics", new User("Wise Eric"));
			CSE360.reply(p2, "DO NOT TRY FORCE", new User("Admin Tom"));
			CSE360.markReply(p2, r1, true);
		
		String p3 = CSE360.post("How do for loops work?","content",u3);
			String r2 = CSE360.reply(p3, "They loop through a set of functions/variables/etc a fixed number of times!", u3);
			CSE360.markReply(p3, r2, true);
			
		String p4 = CSE360.post("Assignment Assistance","content",u4);
		
		String p5 = CSE360.post("When is HW due?","content",theUser);
			CSE360.reply(p5, "TP1 is due 6/8, HW2 is due 6/10", new User("TA"));
		
		String p6 = CSE360.post("Team Norms Megathread","content",u6);
			for(int i = 0; i < 100; i++){CSE360.reply(p6, "basic reply: "+Double.toString(Math.random()).substring(0,4), new User("User"+Double.toString(100*Math.random()).substring(0,2)));}
		
		String p7 = CSE360.post("Why won't anyone respond to me!?!?","content",u5);
			
		updateQuestions();
        
        setup();
	}
	
	/**********
	 * <p> Method: setup() </p>
	 * 
	 * <p> Description: This method is called to reset the page and then populate it with new
	 * content.</p>
	 * 
	 */
	public void setup() {
		theRootPane.getChildren().clear();		
	    theRootPane.getChildren().addAll(
			label_PageTitle, label_UserDetails, 
			button_UpdateThisUser, line_Separator1,
	        line_Separator4, 
	        button_Post, 
	        text_title, text_post, text_keywords,
	        tblPosts, tblReplies,
	        button_Logout, button_Quit
	    );
			
	}
	
	public void updateQuestions(){
		tblPosts.getItems().clear();
		
		for(int i = 0; i < CSE360.getNumQ(); i++){
			Question Q = CSE360.getQindex(i);
			
			tblPosts.getItems().add(new GenericQuestion(Q.getTitle(),Q.getContent(), Q.getPoster().getUserName(),Q.getNumReplies(),Q.getQID()));
		}
	}
	
	public void updateReplies(GenericQuestion GQ){
		tblReplies.getItems().clear();
		
		GenericAnswer GA = new GenericAnswer();
		Answer A = new Answer();
		Question Q = CSE360.getQuestion(GQ.getQID());
		
		for(int i = 0; i < Q.getNumReplies(); i++){
			A = Q.getReply(i);
			GA = new GenericAnswer(A.getMarkedAnswer(), A.getPoster().getUserName(), A.getContent(), GQ.getQID(), A.getRID());
			
			tblReplies.getItems().add(GA);
		}
	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 * 
	 * @param b		The Button object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}

	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}	
	
	/**********************************************************************************************

	User Interface Actions for this page
	
	**********************************************************************************************/
	
	private void performUpdate () {
		if (GUISystemStartUpPage.theUserUpdatePage == null)
			GUISystemStartUpPage.theUserUpdatePage = 
				new GUIUserUpdatePage(primaryStage, theRootPane, theDatabase, theUser);
		else
			GUISystemStartUpPage.theUserUpdatePage.setup();	
	}	

	private void post(String title, String content){
		CSE360.post(title, content, theUser);
	}
	
	private void performLogout() {
		GUISystemStartUpPage.theSystemStartupPage.setup();
	}
	
	private void performQuit() {
		System.exit(0);
	}
}
