package mvc.board.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.model.BoardDAO;
import mvc.test.action.SuperAction;

public class DeleteProAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String passwd = request.getParameter("passwd");
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.boardDelete(num, passwd);
		 
		 request.setAttribute("pageNum", pageNum);
		 request.setAttribute("result", result);
		return "/WEB-INF/views/board/deletePro.jsp";
	}

}
