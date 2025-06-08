/*******
 * <p> Title: Answer Class </p>
 * 
 * <p> Description: This Answer class represents a answer to a post. This class contains
 *  details such as the content, poster, and a reference to the question. </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Asher Hausam
 * 
 * @version 1.0 2025-06-07 : initial commit & 
 */

package crud;
import entityClasses.User;

public class Answer {

	/**
	 * <p> Internal Variables</p>
	 * 
	 * <p> Description: set of reply variables that control internal functioning</p>
	 */
	private User postUser;
	private String RID;
	
	private boolean markedAnswer = false;
	
	private String replyContent="";
	private String[] keywords = new String[0];
	
	public Answer(){
	}
	
	/**
	 * <p> Method: Answer(Question q)</p>
	 * 
	 * <p> Description: reply constructor</p>
	 * 
	 * @param q is the question we're replying to
	 */
	public Answer(Question q){
		this.RID = "ar-" + q.getTitle()+"|"+q.getNextReply();
	}
	
	/**
	 * <p> Method: Answer(Question q)</p>
	 * 
	 * <p> Description: overloaded reply constructor</p>
	 * 
	 * @param poster is the person replying
	 * @param q is the question we're replying to
	 */
	public Answer(User poster, Question q){
		this.postUser = poster;
		this.RID = "r-"+q.getTitle()+"|"+q.getNextReply();
	}
	
	/**
	 * <p> Method: Answer(Question q)</p>
	 * 
	 * <p> Description: overloaded reply constructor</p>
	 * 
	 * @param q is the question we're replying to
	 * @param replyContent is the actual content of the reply
	 * @param poster is the user replying
	 */
	public Answer(String replyContent, Question q){
		anonreply(replyContent, q);
	}
	
	
	/**
	 * <p> Method: Answer(Question q)</p>
	 * 
	 * <p> Description: overloaded reply constructor</p>
	 * 
	 * @param q is the question we're replying to
	 * @param replyContent is the actual content of the reply
	 * @param poster is the user replying
	 */
	public Answer(String replyContent, User poster, Question q){
		this.postUser = poster;
		this.replyContent = "\""+replyContent+"\"";
		this.RID = "r-"+q.getTitle()+"|"+q.getNextReply();
	}
	
	/**
	 * <p> Method: Answer(Question q)</p>
	 * 
	 * <p> Description: overloaded reply constructor</p>
	 * 
	 * @param poster is the person replying
	 * @param q is the question we're replying to
	 */
	private void anonreply(String replyContent, Question q){
		this.replyContent = "\""+replyContent+"\"";
		this.RID = "ar-" + q.getTitle()+"|"+q.getNextReply();
		this.postUser = new User("Anon");
	}
	
	/** <p> Methods: Assorted getters</p>
	 * <p> Description: public read-only getters for various variables</p>
	 * @param n/a
	*/
	public String getContent(){return replyContent;}
	public User getPoster(){return postUser;}
	public String[] getKeywords(){return keywords;}
	public boolean getMarkedAnswer(){return markedAnswer;}
	public String getRID(){return RID;}
	
	
	/** <p> Methods: markAsnwer(boolean marking)</p>
	 * <p> Description: sets reply to marked or unmarked</p>
	 * @param marking sets the status of the answer
	*/
	public boolean markAnswer(boolean marking){
		if(this.markedAnswer && (!marking)){
			this.RID = RID.substring(1, RID.length());
		} else if((!this.markedAnswer) && marking) {
			this.RID = "*" + RID;
		}
		
		this.markedAnswer = marking;
		return markedAnswer;
	}
	
	
	/** <p> Method: rawReply()</p>
	 * <p> Description: returns string with formatted and reply</p>
	 * @param n/a
	*/
	public String rawReply(){ return RID + ": " + this.replyContent + "-" + postUser.getUserName();}
	
}
