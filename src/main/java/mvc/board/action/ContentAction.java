package mvc.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.board.model.BoardDAO;
import mvc.board.model.BoardDTO;
import mvc.test.action.SuperAction;

public class ContentAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sid");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto =  dao.readContent(num);
		
		int ref		= dto.getRef();
		int re_step	= dto.getRe_step();
		int re_level= dto.getRe_level();
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		request.setAttribute("sid", sid);
		return "/WEB-INF/views/board/content.jsp";
	}

}
