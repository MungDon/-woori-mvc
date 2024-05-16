package mvc.board.model;

// 1단계 임포트
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// getInstance() 사용!
public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() { return instance; }
	private BoardDAO() {}
	
	// 사용 객체 변수 미리 선언
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	// 2단계 드라이버 로딩
	private Connection getConn() throws Exception {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");		
			String dburl= "jdbc:oracle:thin:@localhost:1521:orcl";
			String user	= "scott";
			String pw	= "tiger";
			conn = DriverManager.getConnection(dburl,user,pw);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// 6단계 연결 끊기
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try{ if(conn != null) { conn.close(); } }catch(SQLException e) { e.printStackTrace(); }
		try{ if(pstmt != null) { pstmt.close(); } }catch(SQLException e) { e.printStackTrace(); }
		try{ if(rs != null) { rs.close(); } }catch(SQLException e) { e.printStackTrace(); }
	}
	
	
	// 글 작성
	public int boardInsert(BoardDTO dto) {
		int result = 0;

		int num		= dto.getNum();		// 글번호
		int ref		= dto.getRef();		// 그룹번호
		int re_step = dto.getRe_step(); 
		int re_level= dto.getRe_level();
		int number	= 0;	// 새로운 그룹번호
		try {
			conn = getConn();
			// 새 글인 경우는 중복되지않은 새로운 그룹번호 필요
			// 답글인 경우 그룹번호 넘어온다
			sql = "select max(num) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) { 
				number = rs.getInt(1)+1;	
			}else {
				number = 1;
			}
			
			if (num != 0) { // 글번호 0이 아님 -> 글번호가 있다 -> 답글일 경우
				sql="update board set re_step=re_step+1 where ref= ? and re_step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step	= re_step+1;
				re_level= re_level+1;
			}else{
				ref		= number;
				re_step	= 0;
				re_level= 0;
			}
			// readCount (조회수)는 직접 입력 ㄴㄴ
			sql = "insert into board(num, writer, title, content, passwd, ref, re_step, re_level, reg) values(board_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt,rs);
		}
		return result;
	}

	// 글 개수
	public int boardCount() {
		int result = 0;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1); 
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result; 
	}
	
	// 글 목록
	public ArrayList<BoardDTO> boardList(int start, int end){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>(end);
		try {
			conn = getConn();

			sql = "select * from (select b.* , rownum r from (select * from board order by ref desc, re_step asc ) b) where r >= ? and r <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start); 
			pstmt.setInt(2, end); 

			rs = pstmt.executeQuery();
			if (rs.next()) {
				do{ 
					BoardDTO dto = new BoardDTO();
					dto.setNum(rs.getInt("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setReadCount(rs.getInt("readCount"));
					dto.setRef(rs.getInt("ref"));
					dto.setRe_step(rs.getInt("re_step"));
					dto.setRe_level(rs.getInt("re_level"));
					dto.setReg(rs.getTimestamp("reg"));
					list.add(dto); 
				}while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	
	// 글 내용(+조회수)
	public BoardDTO readContent(int num) {
		BoardDTO dto = new BoardDTO();
		try {
			conn = getConn();
			sql = "update board set readcount=readcount+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			sql = "select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setReadCount(rs.getInt("readCount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));	
				dto.setRe_level(rs.getInt("re_level"));
				dto.setReg(rs.getTimestamp("reg"));
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}
	
	// 글 수정Form
	public BoardDTO boardUpForm(int num) {
		BoardDTO dto = new BoardDTO();
		try {
			conn = getConn();
			sql = "select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setReadCount(rs.getInt("readCount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setReg(rs.getTimestamp("reg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}
	
	// 글 수정Pro
	public int boardUpPro(BoardDTO dto) {
		int result = 0;
		String dbpw = "";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement("select passwd from board where num = ?");
			pstmt.setInt(1, dto.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbpw = rs.getString("passwd");
				if(dbpw.equals(dto.getPasswd())) {
					sql = "update board set writer=?, title=?, content=?, passwd=?  where num=? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, dto.getWriter());
					pstmt.setString(2, dto.getTitle());
					pstmt.setString(3, dto.getContent());
					pstmt.setString(4, dto.getPasswd());
					pstmt.setInt(5, dto.getNum());
					result = pstmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}
	
	// 글 삭제 
	public int boardDelete(int num, String passwd) {
		int result = 0;
		String dbpw = "";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement("select passwd from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbpw= rs.getString("passwd");
				if(dbpw.equals(passwd)){
					sql = "delete from board where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					result = pstmt.executeUpdate();
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}

	/*유저가 쓴 게시글 카운트*/
	public int MyBoardCount(String sid) {
		int result = 0;
		try {
			conn = getConn();
			sql ="select count(*) from board where writer = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sid);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return result;
	}
	/*유저가 쓴 게시글 목록*/
	public ArrayList<BoardDTO> MyBoardList(int start, int end, String sid){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			conn = getConn();
			sql = "select * from(select a.*, rownum as r from(select * from board where writer = ? order by ref desc, re_step asc)a) where r >= ? and r <= ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sid);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setReadCount(rs.getInt("readCount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setReg(rs.getTimestamp("reg"));
				list.add(dto); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
}