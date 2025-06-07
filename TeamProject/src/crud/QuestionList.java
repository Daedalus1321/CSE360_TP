/*******
 * <p> Title: Question List Class </p>
 * 
 * <p> Description: This Question class represents a list of questions. This class contains. </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Asher Hausam
 * 
 * @version 1.0 2025-06-07 : initial commit
 */ 

package crud;
import entityClasses.User;

public class QuestionList {
	
	/*******
	 * <p> Internal Variables</p>
	 * 
	 * <p> Description: set of post variables that control internal functioning</p>
	 */
	private String name;
	
	private int numquestions = 0;
	private int nextquestion = 0;
	private Question[] questions = new Question[1];
	
	private AnswerList answerlist;
	
	
   /** <p> Method: Question()</p>
	 * <p> Description: Default constructor for creating the questionList (Unused)</p>
	*/
	public QuestionList(){}
	
	/** <p> Method: Question(String name)</p>
	 * <p> Description: constructor for list with name of list</p>
	*/
	public QuestionList(String name){
		this.name = name;
	}
	
	/** <p> Methods: Assorted getters</p>
	 * <p> Description: public read-only getters for various variables</p>
	 * @param n/a
	*/
	public QuestionList getList(){return this;}
	public int getLength(){return this.numquestions;}
	public String getName(){return this.name;}
	public int getNextQuestion(){return this.nextquestion;}
	public Question getQuestion(String QID){
		for(int i = 0; i < questions.length; i++){
			if(questions[i].getQID().equals(QID)){
				return questions[i];
			}
		}
		return null;
	}
	
	/** <p> Method: post(String title, String postContent, User poster)</p>
	 * <p> Description: function to post to discussion</p>
	 * @param title : post title
	 * @param postContent : post content
	 * @param poster : User posting
	*/
	public String post(String title, String postContent, User poster){
		shiftQuestions();
		resizeArray(numquestions+1);
		
		questions[numquestions] = new Question(title, postContent, poster);
		questions[numquestions].setID("q-" + this.name + "|" + nextquestion);
		nextquestion++;
		numquestions++;
		
		return questions[numquestions-1].getQID();
	}
	
	/** <p> Method: linkReplies(AnswerList replies)</p>
	 * <p> links question list to an answer list</p>
	 * @param replies is the linked AnswerList
	*/
	public void linkReplies(AnswerList replies){
		this.answerlist = replies;
	}
	
	/** <p> Method: deleteQuestion(String QID)</p>
	 * <p> Description: deletes reply and resizes the array</p>
	 * @param ID is unique id of the post
	*/
	public boolean deleteQuestion(String QID){
		for(int i = 0; i < questions.length; i++){
			if(questions[i].getQID().equals(QID)){
				String RIDS[] = new String[questions[i].getNumReplies()];
				
				for(int j = 0; j < questions[i].getNumReplies(); j++){
					RIDS[j] = questions[i].getReplies()[j].getRID();
				}
				
				for(int j = 0; j < RIDS.length; j++){
					questions[i].deleteReply(RIDS[j]);
				}
				
				
				questions[i] = null;
				numquestions--;
				shiftQuestions();
				return true;
			}
		}
		return false;
	}
	
	/** <p> Method: deleteResply(String ID)</p>
	 * <p> Description: deletes reply and resizes the array</p>
	 * @param ID is unique id of the post
	*/
	public boolean deleteReply(String QID, String RID){
		for(int i = 0; i < questions.length; i++){
			if(questions[i].getQID().equals(QID)){
					questions[i].deleteReply(RID);
					answerlist.deleteReply(RID);
					return true;
			}
		}
		return false;
	}
	
	/** <p> Method: update(String postContent, User editor)</p>
	 * <p> Description: updates content IFF user is the original poster or an admin</p>
	 * @param postContent is updated post content
	 * @param editor is user editing the post
	*/
	public String update(String QID, String postContent, User editor){	
		for(int i = 0; i < questions.length; i++){
			if(questions[i].getQID().equals(QID)){
				questions[i].update(postContent, editor);
				return null;
			}
		}
		return "error posting";
	}
	
	/** <p> Method: reply(String postContent, User replier) / reply(Answer reply)</p>
	 * <p> Description: </p>
	 * @param postContent is the content of the reply
	 * @param replier is user replying
	 * @alternate reply(Answer reply) adds functionality for using an Answer instead of individual params
	*/
	public String reply(String QID, String content, User replier){
		for(int i = 0; i < questions.length; i++){
			if(questions[i].getQID().equals(QID)){
				Answer newAns = answerlist.reply(content, replier, questions[i]);
				questions[i].reply(newAns);
				return newAns.getRID();
			}
		}
		
		return "";
	}
	
	
	/** <p> Method: resizeReplies(int newsize)</p>
	 * <p> Description: helper method to resize array to add new questions</p>
	 * @param newsize is size to resize the array to
	*/
	private boolean resizeArray(int newsize){
		if(newsize <= questions.length){
			return false;
		} else {
			Question[] newlist = new Question[newsize];
			for(int i = 0; i < questions.length; i++){
				newlist[i] = questions[i];
			}
			questions = newlist;
			return true;
		}
	}
	
	
	/** <p> Method: shiftQuestions()</p>
	 * <p> Description: shifts over questions in questions to delete @null space</p>
	 * @param n/a
	 * @possible_issue does continue to loop aggressively through nulls
	*/
	private boolean shiftQuestions(){
		Question[] newlist = new Question[numquestions];
		int index = 0;
		
		for(int i = 0; i < questions.length; i++){
			if(questions[i] != null){
				newlist[index] = questions[i];
				index++;
			}
		}
		
		questions = newlist;
		return true;
	}
	
	
	/** <p> Method: markReply(String ID, boolean marking)</p>
	 * <p> Description: marks a reply as an answer</p>
	 * @param ID is unique id of the post
	 * @param marking sets reply to either an answer or strips the answer
	*/
	public boolean markReply(String QID, String RID, boolean marking){
		for(int i = 0; i < questions.length; i++){
			if(questions[i].getQID().equals(QID)){
				questions[i].markReply(RID, marking);
				return true;
			}
		}
		return false;
	}
	
	
	
	/** <p> Method: fullList_raw()</p>
	 * <p> Description: returns string with all questions and replies, formatted</p>
	 * @param n/a
	*/
	public String fullList_raw(){
		String list = "QUESTION LIST - " + this.name + ":\n";
		shiftQuestions();
		
		for(int i = 0; i < numquestions; i++){
			list += "---------------------------------\n";
			list += questions[i].fullPost_raw();
		}
		
		return list + "---------------------------------\n";
	}
	
	/** <p> Method: fullList_justQuestions_raw()</p>
	 * <p> Description: returns string with all questions and replies, formatted</p>
	 * @param n/a
	*/
	public String fullList_justQuestions_raw(){
		String list = "QUESTION LIST (JUST Q's)- " + this.name + ":\n";
		shiftQuestions();
		
		for(int i = 0; i < numquestions; i++){
			list += questions[i].justQuestion_raw();
		}
		
		return list;
	}
}
