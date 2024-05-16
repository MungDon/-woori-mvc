package mvc.test.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Jstl02Action implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		
		// 해당 뷰에서 필요로하는 속성 값
		request.setAttribute("money", 1000000);
		request.setAttribute("pi", Math.PI);
		
		Date day = new Date();
		
		request.setAttribute("day", day);
		
		// 해당 뷰의 경로
		return "/WEB-INF/test/jstl02.jsp";
	}

}
