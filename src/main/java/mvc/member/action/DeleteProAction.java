package mvc.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.member.model.MemberDAO;
import mvc.member.model.MemberDTO;
import mvc.test.action.SuperAction;

public class DeleteProAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sid");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		dto.setPw(request.getParameter("pw"));
		dto.setId(sid);
		int result = dao.deleteMember(dto);
		
		if(result == 1) {
			session.invalidate();
		}
		request.setAttribute("result", result);
		return "/WEB-INF/views/member/deletePro.jsp";
	}

}
