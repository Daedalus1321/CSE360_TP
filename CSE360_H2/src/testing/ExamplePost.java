package testing;
import crud.*;
import entityClasses.User;
import validation.CheckUserPass;

import java.util.Scanner;

public class ExamplePost {
	private static QuestionList CSE360 = new QuestionList("CSE360");
	private static AnswerList CSE360_a = new AnswerList("CSE360 answers");
	static Scanner userInput = new Scanner(System.in);
	static User currentUser;
	
	/** <p> Method: main()</p>
	 * <p> Description: runs program</p>
	*/
	public static void main(String[] args){
		CSE360.linkReplies(CSE360_a);
		
		boolean running = true;
		boolean passValid = false;
		
		String username = "";
		String password = "";
		
		System.out.println("WELCOME TO THE DISCUSSION BOARD!");
		
		
		//Takes password and username validity, loops until it is valid
		while (!passValid){
			System.out.print("Enter your username: ");
			username = userInput.nextLine();
			
			System.out.print("Enter your Password: ");
			password = userInput.nextLine();
			
			String errors = CheckUserPass.checkUserPass(username, password);
			
			if(!errors.equals("")){
				System.out.println("\n" + errors + "\n");
			} else {
				System.out.println("\nPassword/Username accepted, welcome in!\n");
				passValid = true;
			}			
			
		}
		
		//Creates new user with given username and password
		currentUser = new User(username, password, false, true, false, false, false);
		

		//Actual loop that cycles user through choices and inputs
		while (running){
			
			//Gets user selection
			Character choice = selection();
			
			if(choice == 'v'){
				System.out.println("\n===Viewing Messageboard===");
				viewMessageboard();
			} else if(choice == 'c'){
				System.out.println("\n===Posting===");
				post();
			} else if(choice == 'r'){
				System.out.println("\n===Replying===");
				reply();
			} else if(choice == 'u'){
				System.out.println("\n===Updating post===");
				//update();
			} else if(choice == 'd'){
				System.out.println("\n===Deleting post===");
				delete();
			} else if(choice == 'x'){
				System.out.println("\n===Logging Out===");
				System.out.print("[SYSTEM EXIT]");
				running = false;
				break;
			}
		}
		
		userInput.close();
	}
	
	/** <p> Method: reply()</p>
	 * <p> Description: allows user to delete a post</p>
	*/
	public static void reply(){
		
		int count = 0;
		boolean reply = false;
		
		System.out.println("CSE360 posts: ");
		System.out.println("---------------------------------");
		for(int i = 0; i < CSE360.getLength(); i++){
			Question Q = CSE360.getQindex(i);
			System.out.println("\t" + Q.getTitle() + " (ID: " + Q.getQID() + ")");
			count++;
		}
		
		if(count == 0){
			System.out.println("\tThere are no availible posts!");
			System.out.println("---------------------------------\n");
			return;
		}
		
		System.out.println("---------------------------------\n");
		
		System.out.print("Enter a valid post id or # to leave: ");
		String QID = userInput.nextLine();
		
		Question Q;
		
		while (true){
			if(QID.contains("#")){
				break;
			}
			
			Q = CSE360.getQuestion(QID);
			
			if(Q == null){
				System.out.println("Enter a valid post id:");
				QID = userInput.nextLine();
			} else {
				System.out.println("[Question Found!]");
				break;
			}
		}
		
		String replyContent = "";
		
		while(!reply){
			System.out.println("Enter your reply, enter '#' to post:");
			int lines = 0;
			
			while(true){
				String line = userInput.nextLine();
				
				if(line.length() < 1 || line.charAt(0) == '#'){
					break; 
				 }
				
				if(lines > 0){
					replyContent += "\n";
				}
				replyContent += line;
				lines++;
			}
			if(replyContent.length() < 10){
				System.out.println("Error: comment must have more than 10 characters");
			} else {
				reply = true;
			}
		}
		
		CSE360.reply(QID, replyContent, currentUser);
		System.out.println("[REPLY POSTED!]");
	}
	
	
	/** <p> Method: delete()</p>
	 * <p> Description: allows user to delete a post</p>
	*/
	public static void delete(){
		
		int count = 0;
		
		System.out.println("Your posts: ");
		System.out.println("---------------------------------");
		for(int i = 0; i < CSE360.getLength(); i++){
			Question Q = CSE360.getQindex(i);
			
			if(Q.getPoster().equals(currentUser)){
				System.out.println("\t" + Q.getTitle() + " (ID: " + Q.getQID() + ")");
				count++;
			}
		}
		
		if(count == 0){
			System.out.println("\tYou have no posts!");
			System.out.println("---------------------------------\n");
			return;
		}
		
		System.out.println("---------------------------------\n");
		
		System.out.print("Enter a valid post id or # to leave: ");
		String QID = userInput.nextLine();
		
		while (true){
			if(QID.contains("#")){
				break;
			}
			
			if(CSE360.deleteQuestion(QID)){
				System.out.println("[POST DELETED]\n");
				break;
			} else {
				System.out.println("Enter a valid post id:");
				QID = userInput.nextLine();
			}
		}
	}
	
	/** <p> Method: post()</p>
	 * <p> Description: processes user input for posting</p>
	*/
	public static void post(){
		boolean title = false;
		boolean post = false;
		
		String titleContent = "";
		String postContent = "";
		
		
		//Checks title validity, leaves loop when title viable
		while(!title){
			System.out.print("Enter your post title: ");
			titleContent = userInput.nextLine();
			
			if(titleContent.length() > 10){
				title = true;
			} else {
				System.out.println("Error: title must have more than 10 characters");
			}
		}
		
		//Checks post validity and loops through lines until post completed
		while(!post){
			System.out.println("Enter your post, enter '#' to post:");
			int lines = 0;
			
			while(true){
				String line = userInput.nextLine();
				
				if(line.length() < 1 || line.charAt(0) == '#'){
					break; 
				 }
				
				if(lines > 0){
					postContent += "\n";
				}
				postContent += line;
				lines++;
			}
			if(postContent.length() < 20){
				System.out.println("Error: post must have more than 20 characters");
			} else {
				post = true;
			}
		}
		
		CSE360.post(titleContent, postContent, currentUser);
		
		System.out.println("[POST SUCCESS]\n");
	}
	
	
	/**
	 * <p> Method: viewmessageboard()</p>
	 * 
	 * <p> Description: prints messageboard</p>
	 */
	public static void viewMessageboard(){
		String board = CSE360.fullList_raw();
		System.out.println(board);
	}
	
	
	/**
	 * <p> Method: selection()</p>
	 * 
	 * <p> Description: prints the menu of choices and gets the response</p>
	 */
	public static Character selection(){	
		System.out.println("===Menu Options==");
		System.out.println("V: View Posts");
		System.out.println("C: Create Post");
		System.out.println("R: Reply to Post");
		System.out.println("U: Update Post");
		System.out.println("D: Delete Post");
		System.out.println("X: Log out\n");
		
		
		Boolean selected = false;
		Character pick = ' ';
		
		while(!selected){
			String selection = "";
			
			System.out.print("User Choice: ");
			
			selection = userInput.nextLine();
			selection = selection.toLowerCase();
			
			if(selection.length() > 0){
				pick = selection.charAt(0);
			}
			
			if(pick == 'v'||pick == 'c'||pick == 'r'||pick == 'u'||pick == 'd'||pick == 'x'){
				selected = true;
			} else {
				System.out.println("\nSelection invalid!");
			}
		}
		return pick;
	}
	
	/**
	 * <p> Method: test()</p>
	 * 
	 * <p> Prints a test with </p>
	 */
	public static void test(){
		
		User testUser = new User("testUser");
		
		String id1 = CSE360.post("Test Post One", "This is a test post", testUser);
		String id2 = CSE360.post("Help with getters", "How do getter functions work?", testUser);
		String id3 = CSE360.post("I HATE YOU ALL", "You guys suck", new User("Anon"));
		
		CSE360.reply(id1, "test reply", testUser);
		
		String r2 = CSE360.reply(id2, "It's obvious to me, get good", new User("Anon"));
		CSE360.reply(id2, "They're public methods to return private values!", testUser);
		
		System.out.println("==========Initial Post========================");
		System.out.println("|Basic post, including some bad faith replies|");
		System.out.println("==============================================");
		System.out.println(CSE360.fullList_raw());
		
		CSE360.deleteQuestion(id3);
		CSE360.deleteReply(id2, r2);
		
		System.out.println("==========Admin Updates=======================");
		System.out.println("|Removed bad faith replies                   |");
		System.out.println("==============================================");
		
		System.out.println(CSE360.fullList_raw());
		
		System.out.println("==========Raw Answer List=====================");
		System.out.println("|Display raw answer list, should have no bad |");
		System.out.println("==============================================");
		
		System.out.println(CSE360_a.rawList());
		
		System.out.println("==========Raw Question List===================");
		System.out.println("|Display raw question list, should be all pos|");
		System.out.println("==============================================");
		
		System.out.println(CSE360.fullList_justQuestions_raw());
	}
}
