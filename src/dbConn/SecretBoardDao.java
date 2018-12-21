package dbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * DB 의 Member 테이블에 접근하여 CRUD 를 수행하는 클래스
 */
public class SecretBoardDao {
	private Connection conn;
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/*
	 * Constructor - create conn object
	 */
	public SecretBoardDao() {
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
	 * write new content
	 */
	public boolean write(SecretBoardVo vo) {
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
	 * update content
	 */
	public boolean update(SecretBoardVo vo) {
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
	 * appear selected content
	 */
	public SecretBoardVo view(String serial) {
		SecretBoardVo vo = new SecretBoardVo();
		
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
	 * delete content
	 */
	public boolean delete(SecretBoardVo vo) {
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
	 * find 로 (제목 | 내용 | 아이디) 검색하여 List 에 담아 리턴
	 */
	public List<SecretBoardVo> searchList(String find){
		List<SecretBoardVo> searchList = new ArrayList<SecretBoardVo>();
		
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return searchList;
	}
	
	/*
	 * rs 를 전달받아 vo 객체에 세팅 후 vo 객체 반환
	 * 주로 select 쿼리 실행 시 사용됨
	 */
	public SecretBoardVo getSecretBoardVo(ResultSet rs) {
		SecretBoardVo vo = new SecretBoardVo();
		
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return vo;
	}
}
