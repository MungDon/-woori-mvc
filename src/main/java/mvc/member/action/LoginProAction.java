package mvc.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.member.model.MemberDAO;
import mvc.member.model.MemberDTO;
import mvc.test.action.SuperAction;

public class LoginProAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		boolean result = dao.loginCheck(dto);
		
		HttpSession session = request.getSession();
		session.setAttribute("sid", dto.getId());
		
		request.setAttribute("result", result);
		
		return "/WEB-INF/views/member/loginPro.jsp";
	}

}
