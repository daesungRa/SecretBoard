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
		boolean result = false;
		
		try {
			conn.setAutoCommit(false);
			// 필수입력사항인 id, name, pwd, email, phone 만 입력
			// photo, photoori 는 추후 개인프로필 수정에서 등록 가능
			sql = "insert into users (id, name, pwd, email, phone) values ( "
					+ "	?, ?, ?, ?, ? ) ";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPwd());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getPhone());
			int i = ps.executeUpdate();
			
			if (i > 0) {
				result = true;
				// 로그인 성공 시 커밋
				conn.commit();
			} else {
				// 로그인 실패 시 롤백
				conn.rollback();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				// 에러 발생 시 롤백
				conn.rollback();
			} catch (Exception ex2) { }
		} finally {
			try {
				closeRtn();
			} catch (Exception ex) {}
		}
		
		return result;
	}
	
	/*
	 * login
	 * 1 이 리턴되면 로그인 성공, 2 가 리턴되면 비밀번호가 맞지 않음, 3 이 리턴되면 아이디가 존재하지 않음
	 */
	public int login(String id, String pwd) {
		int result = 0;
		try {
			
			// 1 이 리턴되면 로그인 성공, 2 가 리턴되면 비밀번호가 맞지 않음, 3 이 리턴되면 아이디가 존재하지 않음
			sql = "select status from ( "
					+ "	select 1 status from users where id = ? and pwd = ? "
					+ "	union all "
					+ "	select 2 status from users where id = ? "
					+ "	union all "
					+ "	select 3 status from dual "
					+ ") where rownum <= 1"; // 1 ~ 3 의 행이 출력됨. 그 중 우선적으로 필터링된 첫번째 행만 캐치
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ps.setString(3, id);
			rs = ps.executeQuery();
			rs.next(); // ResultSet.next() 메서드를 호출해야 그 값을 불러올 수 있음
			
			result = rs.getInt("status");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				closeRtn();
			} catch (Exception ex) { }
		}
		return result;
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
