package crud;

public class GenericQuestion {
	private String title;
	private String poster;
	private Integer numreplies;
	private String QID;

	public GenericQuestion(){
		title = "";
		poster = "";
		numreplies = 0;
		QID = "";
	}
	
    public GenericQuestion(String title, String poster, Integer numreplies, String QID) {
        this.title = title;
        this.poster = poster;
        this.numreplies = numreplies;
        this.QID = QID;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Integer getNumreplies() {
		return numreplies;
	}

	public void setNumreplies(Integer numreplies) {
		this.numreplies = numreplies;
	}

	public String getQID() {
		return QID;
	}

	public void setQID(String qID) {
		QID = qID;
	}

    
}
