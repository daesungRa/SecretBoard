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
	 * [개인 글 목록]
	 * 매개변수로 입력된 아이디에 해당하는 모든 글을 List 에 담아 반환 > Main 페이지에서 사용
	 * (serial, subject, id, cdate, isPublic)
	 */
	public List<SecretBoardVo> searchAll(String id, int pageNum){
		List<SecretBoardVo> searchList = new ArrayList<SecretBoardVo>();
		int startContent = 1; // 시작 글의 행번호
		
		try {
			// 페이징 처리, 행 삽입 및 정렬
			sql = "select * from ( " // paging
					+ "		select rownum rno, sboard.* from ( " // 행 추가
					+ "			select b.serial, b.subject, b.id, b.cdate, b.ispublic " // 검색 (추후 파일첨부 컬럼 추가 가능)
					+ "				from sboard b "
					+ "				where b.id = ? "
					+ "				order by b.cdate desc " // 검색, 날짜별 desc 정렬
					+ "		) sboard" // 행 추가
					+ "	) where rno between ? and ? "; // paging (1 - 20)
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			// pageNum 에 따른 시작 글의 행번호 세팅
			// 만약 5 페이지라면, 시작 글의 행번호는 81 번이 됨 > 81 - 100 번 글 반환
			startContent += ((pageNum - 1) * 20);
			ps.setInt(2, startContent);
			ps.setInt(3, startContent + 19);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				SecretBoardVo vo = new SecretBoardVo();
				vo.setSerial(rs.getString("serial"));
				vo.setSubject(rs.getString("subject"));
				vo.setId(rs.getString("id"));
				vo.setCdate(rs.getString("cdate"));
				vo.setIsPublic(rs.getInt("ispublic"));
				
				searchList.add(vo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return searchList;
	}
	
	/*
	 * [공개 글 목록]
	 * 매개변수로 입력된 아이디에 해당하는 공개 글을 List 에 담아 반환 > Main 페이지에서 사용
	 * (serial, subject, id, cdate, isPublic)
	 */
	public List<SecretBoardVo> searchPublic(String id){
		List<SecretBoardVo> searchList = new ArrayList<SecretBoardVo>();
		
		try {
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return searchList;
	}
	
	/*
	 * 해당 유저의 모든 게시글 수 반환
	 */
	public int getTotContent(String id) {
		// 0 이면 해당 유저의 글이 존재하지 않음
		int tot = 0;
		
		try {
			sql = " select count(*) cnt from sboard where id = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				tot = rs.getInt("cnt");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return tot;
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
