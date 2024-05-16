package mvc.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.model.MemberDAO;
import mvc.member.model.MemberDTO;
import mvc.test.action.SuperAction;

public class UpdateProAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();	
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setPhone1(request.getParameter("phone1"));
		dto.setPhone2(request.getParameter("phone2"));
		dao.updateMember(dto);
		return "/WEB-INF/views/member/updatePro.jsp";
	}

}
