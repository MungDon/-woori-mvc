package mvc.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.test.action.SuperAction;

public class InsertFormAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		
		return "/WEB-INF/views/member/insertForm.jsp";
		
	}

}
