package mvc.test.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Jstl01Action implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		
		JstlDTO d1 = new JstlDTO();
		d1.setNum(1);
 		d1.setName("java");

 		JstlDTO d2 = new JstlDTO();
 		d2.setNum(2);
 		d2.setName("mvc");
		
 		List<String> list = new ArrayList<String>();
 		list.add("java");
 		list.add("mvc");
 		list.add("spring");
 		list.add("jsp");
 		
 		
 		
		request.setAttribute("d1", d1);
		request.setAttribute("d2", d2);
		request.setAttribute("list", list);
		
		return "/WEB-INF/test/jstl01.jsp";
	}

}
