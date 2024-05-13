package mvc.test.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.test.action.SuperAction;

public class HelloController extends HttpServlet {

	/*
	 * @Override protected void doGet(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { super.doGet(req, resp); }
	 * 
	 * @Override protected void doPost(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { super.doPost(req, resp); }
	 */

	
	
	
	// properties 파일은 map 타입(key : value) 로 이루어져있기때문에 사용
	private Map<String,SuperAction> map = new HashMap<String, SuperAction>();
	
	// init() : 서버 실행 후 요청시의 최초 한번만 동작하는 메서드
	// commandURI.properties 파일 안에있는 모든 값들을 읽어와서 객체 생성 후 다시 map 타입으로 저장함
	@Override
	public void init(ServletConfig config) throws ServletException {
		String propertiesPath = config.getInitParameter("commandURI");// commandURI.properties 의 경로를 저장
		Properties p = new Properties(); // properties 파일 내용 = 기준으로 Key:value 형태로 읽기 위함
		FileInputStream f = null;				// null 로 미리 선언

		try {
			f = new FileInputStream(propertiesPath);
			p.load(f); // key : value 타입으로 읽는 메서드 == properties 파일을 읽음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (f != null) {		// f 가 null 이아니면 즉, f = new FileInputStream(propertiesPath); 이게 null 이 아니면  close() 연결 종료
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Iterator iter = p.keySet().iterator(); // key 만 모음 .. value 클래스 풀네임 = > 객체 생성
		
		// properties 파일의  value 를 반복하며 객체 생성 후 map 대입
		while(iter.hasNext()) {
			
			String key = (String)iter.next(); // 반복자에서 key 하나를 꺼내서 key 에 저장
			String value = p.getProperty(key); // key 에 있는 value 를 가져옴
			
			try {
				Class c = Class.forName(value);
				SuperAction action = (SuperAction)c.newInstance(); // 객체 생성
				map.put(key,action); // action(value) 는 문자아니고 객체
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}
	}
	
	/*세번째 방법*/
	// doPost, doGet 합친거 /서버 실행 후 요청 시 마다 동작
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI(); 		// 현재 브라우저의 요청받은 URI를 가져온다 이 URI는 즉, properties 의 key 이다 => commandURI.properties 파일안에 있는 /mvc/test/jstl02.do 를 의미
		
		// 다형성  
		SuperAction sa  = map.get(uri); 			// 모든 action  class 는 SuperAction 을 구현한다. 
																	// init 에서 대입된 객체 꺼내기
		
		String veiw = sa.action(req, resp);		// action 메서드 호출
		
		req.getRequestDispatcher(veiw).forward(req, resp); // view 로 이동
	}

	
	
	/* 첫번째 방법 */ 
//	// doPost, doGet 합친거 /서버 실행 후 요청 시 마다 동작
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		//초기의 방식 = 지금은 안씀
////		 System.out.println("Controller실행"+req.getRequestURI());
////		 PrintWriter out = resp.getWriter();
////		 out.write("<html>");
////		 out.write("<body>");
////		 out.write("<h1>HelloController</h1>");
////		 out.write("</body>");
////		 out.write("</html>");
////		 out.flush();
////		 out.close();
////		 
////		 req.getRequestDispatcher("test.jsp").forward(req, resp);
//	}

	
	
//	/*두번째 방법*/
//	// doPost, doGet 합친거 /서버 실행 후 요청 시 마다 동작
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// if -else if : 요청 uri 에 맞는 view 로 이동
//		
//		String uri = req.getRequestURI(); // 요청 uri
//		String view = "";
//		
//		if(uri.equals("/mvc/ccc/login.do")) {
//			// dao / dto 등을 호출
//			// 많은 요청 처리 코드들이 필요함 
//			req.getParameter(""); // 값을 불러옴
//			view = "/ccc/login.jsp";
//		}else if(uri.equals("/mvc/ccc/list.do")) {
//			view = "/ccc/list.jsp";
//		}else {
//			view = "/ccc/main.jsp";
//		}
//		
//	}

}
