package crud;

public class GenericQuestion {
	private String title;
	private String poster;
	public String content;
	private Integer numreplies;
	private String QID;

	public GenericQuestion(){
		title = "";
		poster = "";
		content = "";
		numreplies = 0;
		QID = "";
	}
	
    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public GenericQuestion(String title, String content, String poster, Integer numreplies, String QID) {
        this.title = title;
        this.content = content;
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
