package dbConn;

/*
 * DB 의 SecretBoard 테이블의 데이터를 담는 클래스
 */
public class SecretBoardVo {
	private String serial; // PK
	private String subject;
	private String content;
	private String id; // FK, NN
	private boolean isPublic; // private == false, public == true
	private String cdate; // default sysdate
	private String tags;

	/*
	 * constructor
	 */
	public SecretBoardVo() { }
	public SecretBoardVo(String subject, String content, String id, boolean isPublic, String tags) { // serial 은 자동증가
		this.subject = subject;
		this.content = content;
		this.id = id;
		this.isPublic = isPublic;
		this.tags = tags;
	}

	/*
	 * getter and setter
	 */
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
}