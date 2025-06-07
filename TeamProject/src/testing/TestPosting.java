package testing;
import crud.*;
import entityClasses.User;

public class TestPosting {
	public static void main(String[] args){
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
