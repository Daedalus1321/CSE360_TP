package guiPageClasses;

import java.util.ArrayList;

import applicationMainMethodClasses.FCMainClass;
import crud.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	private Stage primaryStage;	
	private Pane theRootPane;
	private Database theDatabase;
	private User theUser;
	
	private TableView<Question> tblPosts = new TableView<>();

	
	
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
        
        
    	TableColumn<Question, String> colTitle = new TableColumn<>("Title");
    	TableColumn<Question, String> colPoster = new TableColumn<>("Poster");
    	TableColumn<Question, Integer> colNumReplies = new TableColumn<>("# Replies");
    	TableColumn<Question, String> colQID = new TableColumn<>("QID");
        
    	//ArrayList<Questions> userList = theDatabase.getUserListDetails();
    	//ObservableList<Question> observableUserList = FXCollections.observableArrayList(userList);
    	
        colTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        colPoster.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        //colNumReplies.setCellValueFactory(cellData --> new SimpleIntegerProperty(cellData.getValue().getNumReplies()));
        colQID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        
        tblPosts.getColumns().setAll(colTitle, colPoster, colNumReplies, colQID);
        tblPosts.setLayoutX(10);
        tblPosts.setLayoutY(200);
        tblPosts.setMinSize(WINDOW_WIDTH-20, WINDOW_HEIGHT-(20+tblPosts.getLayoutY()));
        
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
			label_PageTitle, label_UserDetails, button_UpdateThisUser, line_Separator1,
	        line_Separator4, 
	        button_Logout,
	        button_Quit, tblPosts
	    );
			
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

	
	private void performLogout() {
		GUISystemStartUpPage.theSystemStartupPage.setup();
	}
	
	private void performQuit() {
		System.exit(0);
	}
}
