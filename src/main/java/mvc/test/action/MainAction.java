package mvc.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction implements SuperAction{
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		// main.jsp 에서 실행되어야할 내용 java 코드
		// dao / dto 등등
		return "/WEB-INF/views/member/main.jsp";
	}
}
//http://localhost:8080/mvc/WEB-INF/views/member/main.jsp
// 이렇게하면 WEB-INF 밑은 인식 불가능