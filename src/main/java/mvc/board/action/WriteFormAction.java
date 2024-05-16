package mvc.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.test.action.SuperAction;

public class WriteFormAction implements SuperAction {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		// jsp 에서의 자바코드부분
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sid");
		
		int num=0,ref=1,re_step=0,re_level=0;
			if(request.getParameter("num") != null){	// 답글일 경우
				num		= Integer.parseInt(request.getParameter("num"));
				ref		= Integer.parseInt(request.getParameter("ref"));
				re_step	= Integer.parseInt(request.getParameter("re_step"));
				re_level= Integer.parseInt(request.getParameter("re_level"));
			}
			
			request.setAttribute("num", new Integer(num));
			request.setAttribute("ref", new Integer(ref));
			request.setAttribute("re_step", new Integer(re_step));
			request.setAttribute("re_level", new Integer(re_level));
			request.setAttribute("sid", sid);
			
			// 보낼 뷰 페이지 경로
			return "/WEB-INF/views/board/writeForm.jsp";
	}
}
