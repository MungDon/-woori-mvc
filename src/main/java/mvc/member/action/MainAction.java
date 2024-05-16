package mvc.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.test.action.SuperAction;

public class MainAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sid");
		request.setAttribute("sid", sid);
		return "/WEB-INF/views/member/main.jsp";
	}

}
