package mvc.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.test.action.SuperAction;

public class DeleteFormAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		return "/WEB-INF/views/board/deleteForm.jsp";
	}

}
