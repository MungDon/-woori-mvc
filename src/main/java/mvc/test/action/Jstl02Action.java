package mvc.test.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Jstl02Action implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("money", 1000000);
		request.setAttribute("pi", Math.PI);
		
		Date day = new Date();
		
		request.setAttribute("day", day);
		return "/WEB-INF/test/jstl02.jsp";
	}

}
