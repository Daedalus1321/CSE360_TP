/*******
 * <p> Title: Answer List Class </p>
 * 
 * <p> Description: This Question class represents a list of answers. </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Asher Hausam
 * 
 * @version 1.0 2025-06-07 : initial commit
 */ 

package crud;
import entityClasses.User;

public class AnswerList {
	
	/*******
	 * <p> Internal Variables</p>
	 * 
	 * <p> Description: set of post variables that control internal functioning</p>
	 */
	private String name;
	
	private int numanswers = 0;
	private Answer[] answers = new Answer[1];
	
	
   /** <p> Method: Question()</p>
	 * <p> Description: Default constructor for creating the questionList (Unused)</p>
	*/
	public AnswerList(){}
	
	/** <p> Method: Question(String name)</p>
	 * <p> Description: constructor for list with name of list</p>
	*/
	public AnswerList(String name){
		this.name = name;
	}
	
	/** <p> Methods: Assorted getters</p>
	 * <p> Description: public read-only getters for various variables</p>
	 * @param n/a
	*/
	public AnswerList getList(){return this;}
	public int getLength(){return this.numanswers;}
	public String getName(){return this.name;}
	public Answer getAnswer(String ID){
		for(int i = 0; i < answers.length; i++){
			if(answers[i].getRID().equals(ID)){
				return answers[i];
			}
		}
		return null;
	}
	
	
	public Answer reply(String replyContent, User poster, Question q){
		shiftAnswers();
		resizeArray(numanswers+1);
		answers[numanswers] = new Answer(replyContent, poster, q);
		numanswers++;
		return answers[numanswers-1];
	}
	
	public void anonReply(String replyContent, Question q){
		shiftAnswers();
		resizeArray(numanswers+1);
		
		answers[numanswers] = new Answer(replyContent, q);
		numanswers++;
	}
	
	/** <p> Method: deleteReplies(String RID)</p>
	 * <p> Description: helper method to resize array to add new questions</p>
	 * @param newsize is size to resize the array to
	*/
	public void deleteReply(String RID){
		shiftAnswers();
		
		for(int i = 0; i < answers.length; i++){
			if(answers[i] != null && answers[i].getRID().equals(RID)){
				answers[i] = null;
				shiftAnswers();
			}
		}
	}
	
	
	/** <p> Method: resizeReplies(int newsize)</p>
	 * <p> Description: helper method to resize array to add new questions</p>
	 * @param newsize is size to resize the array to
	*/
	private boolean resizeArray(int newsize){
		if(newsize <= answers.length){
			return false;
		} else {
			Answer[] newlist = new Answer[newsize];
			for(int i = 0; i < answers.length; i++){
				newlist[i] = answers[i];
			}
			answers = newlist;
			return true;
		}
	}
	
	
	/** <p> Method: shiftQuestions()</p>
	 * <p> Description: shifts over questions in questions to delete @null space</p>
	 * @param n/a
	 * @possible_issue does continue to loop aggressively through nulls
	*/
	private boolean shiftAnswers(){
		Answer[] newlist = new Answer[numanswers];
		int index = 0;
		
		for(int i = 0; i < answers.length; i++){
			if(answers[i] != null){
				newlist[index] = answers[i];
				index++;
			}
		}
		
		answers = newlist;
		return true;
	}
	
	
	/** <p> Method: markReply(String ID, boolean marking)</p>
	 * <p> Description: marks a reply as an answer</p>
	 * @param ID is unique id of the post
	 * @param marking sets reply to either an answer or strips the answer
	*/
	public boolean markAnswer(String RID, boolean marking){
		for(int i = 0; i < answers.length; i++){
			if(answers[i].getRID().equals(RID)){
				answers[i].markAnswer(marking);
				return true;
			}
		}
		return false;
	}
	
	
	
	/** <p> Method: rawList()</p>
	 * <p> Description: returns string with all questions and replies, formatted</p>
	 * @param n/a
	*/
	public String rawList(){
		String list = "REPLY LIST - " + this.name + ":\n";
		this.shiftAnswers();
		
		for(int i = 0; i < numanswers; i++){
			if(answers[i] != null){list += "\t" + answers[i].rawReply() + "\n";}
		}
		
		return list;
	}
}
