package mvc.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.board.model.BoardDAO;
import mvc.board.model.BoardDTO;
import mvc.test.action.SuperAction;

public class ListAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		// jsp 에 있는 자바 코드 내용
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sid");
		
		
		// 페이지 번호
	    String pageNum = request.getParameter("pageNum");
	    if (pageNum == null) {
	        pageNum = "1";
	    }

	    // 한페이지에 보여질 글의 수
	    int pageSize = 10;
	    
	    int currentPage = Integer.parseInt(pageNum);
	    
	    // 한 페이지의 시작 글 번호
	    int startRow	= (currentPage - 1) * pageSize + 1;
	    // 한페이지의 마지막 글 번호
	    int endRow	= currentPage * pageSize;
	    // 총 글 개수
	    int count	= 0;
	    
	    int num = 0;

	    ArrayList<BoardDTO> list = null;
	    BoardDAO dao = BoardDAO.getInstance();
	    
	    // 전체 글 개수
	    count = dao.boardCount();
	    if (count > 0) { // 글이 있는경우
	    	list = dao.boardList(startRow, endRow);
	    }
		
	   // 해당 뷰에서 사용할 속성
	    request.setAttribute("pageSize", new Integer(pageSize));
	    request.setAttribute("currentPage", new Integer(currentPage));
	    request.setAttribute("startRow", new Integer(startRow));
	    request.setAttribute("endRow", new Integer(endRow));
	    request.setAttribute("count",new Integer(count));
	    request.setAttribute("num", new Integer(num));
	    request.setAttribute("list", list);
	    request.setAttribute("sid", sid);
	    
		// 보낼 뷰 페이지의 경로
		return "/WEB-INF/views/board/list.jsp";
	}

}
