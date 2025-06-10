package testing;
import crud.*;
import entityClasses.User;
import validation.CheckUserPass;

import java.util.Scanner;

public class ExamplePost {
	public static void main(String[] args){
		boolean running = true;
		boolean passValid = false;
		
		Scanner userInput = new Scanner(System.in);
		
		String username = "anon";
		String password = "na";
		
		System.out.println("WELCOME TO THE DISCUSSION BOARD!");
		
		while (!passValid){
			System.out.print("Enter your username: ");
			username = userInput.nextLine();
			
			System.out.print("Enter your Password: ");
			password = userInput.nextLine();
			
			String errors = CheckUserPass.checkUserPass(username, password);
			
			if(!errors.equals("")){
				System.out.println(errors);
			} else {
				System.out.println("\nPassword/Username accepted, welcome in!");
				passValid = true;
			}
			
		}
		
		User thisUser = new User(username, password, false, true, false, false, false);
		
		while (running){
			
		}
		
		userInput.close();
	}

	public String selection(){
		String selection = "";
		
		while(selection.equals("")){
			
		}
		
		return selection;
	}
	
	public static void test(){
		QuestionList CSE360 = new QuestionList("CSE360");
		AnswerList CSE360_a = new AnswerList("CSE360 answers");
		CSE360.linkReplies(CSE360_a);
		
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
