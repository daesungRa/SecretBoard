package dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * DB 의 Member 테이블에 접근하여 CRUD 를 수행하는 클래스
 */
public class UsersDao {
	private Connection conn;
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/*
	 * Constructor - create conn object
	 */
	public UsersDao() {
		this.conn = new DBConn().getConn();
	}
	
	/*
	 * closeRtn - 더 이상 사용하지 않는 자원을 close
	 */
	public void closeRtn() {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 * creat new account
	 */
	public boolean insert(UsersVo vo) {
		boolean b = false;
		
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex2) { }
		} finally {
			try {
				closeRtn();
			} catch (Exception ex) {}
		}
		
		return b;
	}
	
	/*
	 * modify user info
	 */
	public boolean modify(UsersVo vo) {
		boolean b = false;
		
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex2) { }
		} finally {
			try {
				closeRtn();
			} catch (Exception ex) {}
		}
		
		return b;
	}
	
	/*
	 * appear user info
	 */
	public UsersVo view(String id) {
		UsersVo vo = new UsersVo();
		
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex2) { }
		} finally {
			try {
				closeRtn();
			} catch (Exception ex) {}
		}
		
		return vo;
	}
	
	/*
	 * delete user info
	 */
	public boolean delete(UsersVo vo) {
		boolean b = false;
		
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex2) { }
		} finally {
			try {
				closeRtn();
			} catch (Exception ex) {}
		}
		
		return b;
	}
	
	/*
	 * rs 를 전달받아 vo 객체에 세팅 후 vo 객체 반환
	 */
	public UsersVo getUsersVo(ResultSet rs) {
		UsersVo vo = new UsersVo();
		
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return vo;
	}
}
