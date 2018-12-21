package dbConn;

/*
 * DB 의 Member 테이블의 데이터를 담는 클래스
 */
public class UsersVo {
	private String id; // PK
	private String name; // NN
	private String pwd; // NN
	private String email; // UK, NN
	private String phone; // UK, NN
	private String photo;
	private String photoOri;
	private String joinDate; // default sysdate
	
	/*
	 * constructor
	 */
	public UsersVo () { }
	public UsersVo (String id, String name, String pwd, String email, String phone, String photo) { // column of PK or not null
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
		this.photo = photo;
	}
	
	/*
	 * override equals and hashcode
	 */
	@Override
	public boolean equals(Object obj) {
		boolean b = false;
		
		if (obj instanceof UsersVo) {
			UsersVo vo = (UsersVo) obj;
			if (vo.getId().equals(this.id) && vo.getName().equals(this.name)) {
				b = true;
			}
		}
		
		return b;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode() + this.name.hashCode();
	}
	
	/*
	 * getter and setter
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhotoOri() {
		return photoOri;
	}
	public void setPhotoOri(String photoOri) {
		this.photoOri = photoOri;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
}
