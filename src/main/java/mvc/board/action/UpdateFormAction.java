package mvc.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.model.BoardDAO;
import mvc.board.model.BoardDTO;
import mvc.test.action.SuperAction;

public class UpdateFormAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto =  dao.boardUpForm(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		return "/WEB-INF/views/board/updateForm.jsp";
	}

}
