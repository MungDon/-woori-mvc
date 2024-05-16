package mvc.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.test.action.SuperAction;

public class LogoutAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		if(session != null) {
			result = 1;
		}
		request.setAttribute("result", result);
		return "/WEB-INF/views/member/logout.jsp";
	}

}
