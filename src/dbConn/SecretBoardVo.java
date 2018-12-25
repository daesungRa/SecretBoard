package dbConn;

/*
 * DB 의 SecretBoard 테이블의 데이터를 담는 클래스
 */
public class SecretBoardVo {
	private String serial; // PK
	private String subject;
	private String content;
	private String id; // FK, NN
	private String pwd; // NN
	private int isPublic; // private 0, public 1
	private String cdate; // default sysdate

	/*
	 * constructor
	 */
	public SecretBoardVo() { }
	public SecretBoardVo(String subject, String content, String id, String pwd, int isPublic) { // serial 은 자동증가
		this.subject = subject;
		this.content = content;
		this.id = id;
		this.pwd = pwd;
		this.isPublic = isPublic;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	
}