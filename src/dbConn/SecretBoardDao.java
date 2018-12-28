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
		boolean result = false;
		
		try {
			conn.setAutoCommit(false);
			
			sql = "insert into sboard (serial, subject, content, id, ispublic, tags) values "
					+ "	(seq_sboard.nextval, ?, ?, ?, ?, ?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getSubject());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getId());
			
			// true 면 1 대입, 아니면 0 대입
			if (vo.getIsPublic()) {
				ps.setString(4, "1");
			} else {
				ps.setString(4, "0");
			}
			
			ps.setString(5, vo.getTags());
			
			int i = ps.executeUpdate();
			
			// 실행 성공한 DML 행 수만큼 반환됨
			if (i > 0) {
				result = true;
				
				conn.setAutoCommit(true);
				conn.commit();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (Exception ex2) { }
		} finally {
			try {
				closeRtn();
			} catch (Exception ex) {}
		}
		
		return result;
	}
	
	/*
	 * update content
	 */
	public boolean update(SecretBoardVo vo) {
		boolean result = false;
		
		try {
			conn.setAutoCommit(false);
			sql = "update sboard "
					+ "	set subject = ?, " // 제목
					+ "		content = ?, " // 내용
					+ "		tags = ?, " // 태그
					+ "		ispublic = ?, " // 공개여부
					+ "		cdate = sysdate " // 수정일 자동적용
					+ "	where serial = ? "; // serial 로 글 판단
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getSubject());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getTags());
			
			// true 면 1 대입, 아니면 0 대입
			if (vo.getIsPublic()) {
				ps.setString(4, "1");
			} else {
				ps.setString(4, "0");
			}
			
			ps.setInt(5, Integer.parseInt(vo.getSerial())); // 사실 serial 은 NUMBER 형이었던 것..
			
			int i = ps.executeUpdate();
			
			// 실행 성공한 DML 행 수만큼 반환됨
			if (i > 0) {
				result = true;
				
				conn.setAutoCommit(true);
				conn.commit();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (Exception ex2) { }
		} finally {
			try {
				closeRtn();
			} catch (Exception ex) {}
		}
		
		return result;
	}
	
	/*
	 * appear selected content
	 */
	public SecretBoardVo view(String serial) {
		SecretBoardVo vo = new SecretBoardVo();
		
		try {
			sql = " select serial, subject, content, id, to_char(cdate, 'yyyy/mm/dd') as cdate, ispublic, tags "
					+ "	from sboard where serial = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, serial);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				vo.setSerial(rs.getString("serial"));
				vo.setSubject(rs.getString("subject"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setCdate(rs.getString("cdate"));

				// '0' or '1' 로 반환되는 문자열에 따라 boolean 형 데이터 저장
				String isPublic = rs.getString("ispublic");
				if (isPublic.equals("0")) { // 0 == false == private
					vo.setIsPublic(false);
				} else if (isPublic.equals("1")) { // 1 == true == public
					vo.setIsPublic(true);
				}
				
				vo.setTags(rs.getString("tags"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
	public boolean delete(String serial) {
		boolean result = false;
		
		try {
			conn.setAutoCommit(false);
			
			sql = " delete sboard where serial = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, serial);
			
			int i = ps.executeUpdate();
			
			// 실행 성공한 DML 행 수만큼 반환됨
			if (i > 0) {
				result = true;
				
				conn.setAutoCommit(true);
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (Exception ex2) { }
		} finally {
			try {
				closeRtn();
			} catch (Exception ex) {}
		}
		
		return result;
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
				
				// '0' or '1' 로 반환되는 문자열에 따라 boolean 형 데이터 저장
				String isPublic = rs.getString("ispublic");
				if (isPublic.equals("0")) { // 0 == false == private
					vo.setIsPublic(false);
				} else if (isPublic.equals("1")) { // 1 == true == public
					vo.setIsPublic(true);
				}
				
				searchList.add(vo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return searchList;
	}
	
	public List<SecretBoardVo> searchByDate(String id, String format){
		List<SecretBoardVo> searchList = new ArrayList<SecretBoardVo>();
		int startContent = 1; // 시작 글의 행번호
		
		try {
			// 페이징 처리, 행 삽입 및 정렬
			sql = "select * from ( " // paging
					+ "		select rownum rno, sboard.* from ( " // 행 추가
					+ "			select b.serial, b.subject, b.id, b.cdate, b.ispublic " // 검색 (추후 파일첨부 컬럼 추가 가능)
					+ "				from sboard b "
					+ "				where b.id = ? and to_char(b.cdate, 'yyyy/mm/dd') = ? "
					+ "				order by b.cdate desc " // 검색, 날짜별 desc 정렬
					+ "		) sboard" // 행 추가
					+ "	) where rno between ? and ? "; // paging (1 - 20)
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, format);
			ps.setInt(3, startContent);
			ps.setInt(4, startContent + 19);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				SecretBoardVo vo = new SecretBoardVo();
				vo.setSerial(rs.getString("serial"));
				vo.setSubject(rs.getString("subject"));
				vo.setId(rs.getString("id"));
				vo.setCdate(rs.getString("cdate"));
				
				// '0' or '1' 로 반환되는 문자열에 따라 boolean 형 데이터 저장
				String isPublic = rs.getString("ispublic");
				if (isPublic.equals("0")) { // 0 == false == private
					vo.setIsPublic(false);
				} else if (isPublic.equals("1")) { // 1 == true == public
					vo.setIsPublic(true);
				}
				
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
