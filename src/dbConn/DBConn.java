package dbConn;

import java.sql.Connection;
import java.sql.DriverManager;

/* 
 * JDBC 드라이버를 통해 Connection 객체를 생성하여 반환하는 클래스
 */
public class DBConn {
	Connection conn;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // orcl 은 정식 버전
	String dbUser = "hr";
	String dbPwd = "hr";
	
	public DBConn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (Exception ex) { ex.printStackTrace(); }
	}
	
	public Connection getConn() {
		return this.conn;
	}
}