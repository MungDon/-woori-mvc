package mvc.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.member.model.MemberDAO;
import mvc.member.model.MemberDTO;
import mvc.test.action.SuperAction;

public class UpdateFormAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sid");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		
		dto = dao.idInfo(sid);
		request.setAttribute("dto", dto);
		request.setAttribute("sid", sid);
		return "/WEB-INF/views/member/updateForm.jsp";
	}

}
