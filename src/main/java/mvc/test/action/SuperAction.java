package mvc.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SuperAction {
	
	// 앞으로 생성될 모든 action 클래스는 이 클래스의 action method 를 구현해야함
	public String action(HttpServletRequest request , HttpServletResponse response);
	
	}
