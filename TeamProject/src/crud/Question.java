/*******
 * <p> Title: Question Class </p>
 * 
 * <p> Description: This Question class represents a question. This class contains
 *  details such as the content, poster, and references to all replies. </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Asher Hausam
 * @TODO implement ID
 * 
 * @version 1.0 2025-06-07 : initial commit
 */ 

package crud;
import entityClasses.User;

public class Question {
	
	/*******
	 * <p> Internal Variables</p>
	 * 
	 * <p> Description: set of post variables that control internal functioning</p>
	 */
	private User postUser;
	private String QID = null;
	private String postTitle="[no title]";
	private String postContent="[no content]";
	private String[] keywords = new String[0];
	
	private Integer numreplies = 0;
	private int nextReply = 0;
	private Answer[] replies = new Answer[1];
	
	public Question(){}
	
   /** <p> Method: Question()</p>
	 * <p> Description: Default constructor for creating the question</p>
	 * @param poster is user creating the post
	*/
	public Question(User poster){
		this.postUser = poster;
	}
	
	/** <p> Method: create(String title, String postContent, User poster)</p>
	 * <p> Description: loaded constructor </p>
	 * @param title is post title
	 * @param postContent is question content
	 * @param poster is user creating the post
	*/
	public Question(String title, String postContent, User poster){
		this.postTitle = title;
		this.postContent = "\""+postContent+"\"";
		this.postUser = poster;
	}
	
	/** <p> Methods: Assorted getters</p>
	 * <p> Description: public read-only getters for various variables</p>
	 * @param n/a
	*/
	public String getTitle(){return postTitle;}
	public String getContent(){return postContent;}
	public User getPoster(){return postUser;}
	public String[] getKeywords(){return keywords;}
	public Answer[] getReplies(){return replies;}
	public Integer getNumReplies(){return numreplies;}
	public int getNextReply(){return nextReply;}
	public String getQID(){return QID;}
	
	/** <p> Methods: Assorted setters</p>
	 * <p> Description: public warite-only setters for various variables</p>
	 * @param n/a
	*/
	public String setID(String newQID){this.QID = newQID; return QID;}
	
	/** <p> Method: update(String postContent, User editor)</p>
	 * <p> Description: updates content IFF user is the original poster or an admin</p>
	 * @param postContent is updated post content
	 * @param editor is user editing the post
	*/
	public String update(String postContent, User editor){	
		if(editor.getUserName() == postUser.getUserName() || editor.getAdminRole() == true || editor.getInstructorRole() == true){
			this.postContent = "\""+postContent+"\"";
			return null;
		} else {
			return "error posting";
		}
	}
	
	/** <p> Method: setKeywords(String[] keywords)</p>
	 * <p> Description: keyword utility for searching</p>
	 * @param keywords are the keywords for the post
	*/
	public boolean setKeywords(String[] keywords){
		this.keywords = keywords;
		return true;
	}
	
	/** <p> Method: reply(String postContent, User replier) / reply(Answer reply)</p>
	 * <p> Description: </p>
	 * @param postContent is the content of the reply
	 * @param replier is user replying
	 * @alternate reply(Answer reply) adds functionality for using an Answer instead of individual params
	*/
	public void reply(String postContent, User replier){
		resizeReplies(numreplies+1);
		Answer reply = new Answer(postContent, replier, this);
		replies[numreplies] = reply;
		numreplies++;
		nextReply++;
	}
	public void reply(Answer reply){
		resizeReplies(numreplies+1);
		replies[numreplies] = reply;
		numreplies++;
		nextReply++;
	}
	
	
	/** <p> Method: resizeReplies(int newsize)</p>
	 * <p> Description: helper method to resize array to add new replies</p>
	 * @param newsize is size to resize the array to
	*/
	private boolean resizeReplies(int newsize){
		if(newsize <= replies.length){
			return false;
		} else {
			Answer[] newlist = new Answer[newsize];
			for(int i = 0; i < replies.length; i++){
				newlist[i] = replies[i];
			}
			replies = newlist;
			return true;
		}
	}
	
	
	/** <p> Method: shiftReplies()</p>
	 * <p> Description: shifts over replies to delete @null space</p>
	 * @param n/a
	 * @possible_issue does continue to loop aggressively through nulls
	*/
	private boolean shiftReplies(){
		Answer[] newlist = new Answer[numreplies];
		int index = 0;
		
		for(int i = 0; i < replies.length; i++){
			if(replies[i] != null){
				newlist[index] = replies[i];
				index++;
			}
		}
		
		replies = newlist;
		return true;
	}
	
	
	/** <p> Method: markReply(String ID, boolean marking)</p>
	 * <p> Description: marks a reply as an answer</p>
	 * @param ID is unique id of the post
	 * @param marking sets reply to either an answer or strips the answer
	*/
	public boolean markReply(String RID, boolean marking){
		for(int i = 0; i < numreplies; i++){
			if(replies[i].getRID().equals(RID)){
				replies[i].markAnswer(marking);
				sortReplies();
				return true;
			}
		}
		
		return false;
	}
	
	
	/** <p> Method: deleteResply(String ID)</p>
	 * <p> Description: deletes reply and resizes the array</p>
	 * @param ID is unique id of the post
	*/
	public boolean deleteReply(String RID){
		for(int i = 0; i < numreplies; i++){
			if(replies[i].getRID().equals(RID)){
				replies[i] = null;
				numreplies--;
				shiftReplies();
				return true;
			}
		}
		return false;
	}
	
	/** <p> Method: sortReplies()</p>
	 * <p> Description: re-orders replies to have marked results first</p>
	 * @param n/a
	*/
	public int sortReplies(){
		int markedReplies = 0;
		
		for(int i = 0; i < replies.length; i++){
			if(replies[i].getRID().charAt(0) == '*'){
				Answer moving = replies[i];
				for(int j = i; j > markedReplies; j--){
					replies[j] = replies[j-1];
				}
				replies[markedReplies] = moving;
				markedReplies++;
			}
		}
		
		
		return markedReplies;
	}
	
	
	/** <p> Method: fullPost_raw()</p>
	 * <p> Description: returns string with formatted post and replies</p>
	 * @param n/a
	*/
	public String fullPost_raw(){
		String post_raw = postTitle + " - " + postUser.getUserName() + ":\n\t" + postContent + "\n";
		
		String replies_raw = "   --Replies:\n";
		
		for(int i = 0; i < numreplies; i++){
			replies_raw += "\t" + replies[i].rawReply() + "\n";
		}
		
		return post_raw + replies_raw;
	}
	
	/** <p> Method: justQuestion()</p>
	 * <p> Description: returns string with formatted post and replies</p>
	 * @param n/a
	*/
	public String justQuestion_raw(){
		return postTitle + " - " + postUser.getUserName() + ":\n\t" + postContent + "\n";
	}
}
