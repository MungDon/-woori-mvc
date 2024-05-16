package mvc.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.board.model.BoardDAO;
import mvc.board.model.BoardDTO;
import mvc.test.action.SuperAction;

public class MyListAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sid");

		int pageSize = 10;	// 한페이지에 보여줄 게시글 수
	    String pageNum = request.getParameter("pageNum");
	    if (pageNum == null) {// 받아오는 페이지번호가 없다면 해당페이지가 1번이다
	        pageNum = "1";
	    }

	    int currentPage = Integer.parseInt(pageNum);// 현재페이지
	    int startRow	= (currentPage - 1) * pageSize + 1; // 해당페이지의 게시글 시작번호
	    int endRow	= currentPage * pageSize;// 해당페이지의 게시글 끝번호
	    int count	= 0;// 총 게시글 수

	    ArrayList<BoardDTO> list = null; // 리스트객체 초기화
	    BoardDAO dao = BoardDAO.getInstance();// dao 객체 호출
	    count = dao.MyBoardCount(sid);// count 변수에 db 에서 가져온 모든 게시글 수 저장 
	    if (count > 0) {// 게시글이있다면
	    	list = dao.MyBoardList(startRow, endRow, sid);//리스트 db에서 뽑아온걸 list에 저장
	    }
	    request.setAttribute("count", count);
	    request.setAttribute("sid", sid);
	    request.setAttribute("list", list);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("pageSize", pageSize);
 	    
		return "/WEB-INF/views/board/mylist.jsp";
	}

}
