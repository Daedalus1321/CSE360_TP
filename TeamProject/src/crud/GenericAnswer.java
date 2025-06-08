package crud;

public class GenericAnswer {
	private String poster;
	private String QID;
	private String RID;
	private Boolean markedAnswer;
	private String content;
	
	public GenericAnswer(){
		poster = "";
		QID = "";
		RID = "";
		markedAnswer = false;
		content = "";
	}
	public GenericAnswer(Boolean markedAnswer, String poster, String content, String QID, String RID){
		this.poster = poster;
		this.QID = QID;
		this.RID = RID;
		this.markedAnswer = markedAnswer;
		this.content = content;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getQID() {
		return QID;
	}
	public void setQID(String qID) {
		QID = qID;
	}
	public String getRID() {
		return RID;
	}
	public void setRID(String rID) {
		RID = rID;
	}
	public Boolean getMarkedAnswer() {
		return markedAnswer;
	}
	public void setMarkedAnswer(Boolean markedAnswer) {
		this.markedAnswer = markedAnswer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
