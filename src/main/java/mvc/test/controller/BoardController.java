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

public class BoardController extends HttpServlet{
	
	//properties 파일은 Map 타입이다
	
	private Map<String, SuperAction> map = new HashMap<String,SuperAction>();

	// 서버 실행 후 요청 - > 최초 한번만 동작
	@Override
	public void init(ServletConfig config) throws ServletException {
		// ServletConfig config 설정 -> 전체 설정인  web.xml 에서 전달된 파라미터
		
		String propertiesPath = config.getInitParameter("boardURI");
		
		// properties 생성
		// properties 파일의 내용을 =(기호) 기준으로 key = value 로 읽기 위해 생성
		Properties p = new Properties();
		
		// properties 파일을 읽기 위한 FileInputStream 생성 전 변수 선언
		FileInputStream f = null;
		
		try {
			// propertiesPath 변수 사용하여 생성
			// 예외처리 필수 FileNotFoundException
			f = new FileInputStream(propertiesPath);
			
			//예외처리 필수 IOException 예외 최고 조상인 Exception 으로 한번에 처리
			//key = value 로 읽는다 
			p.load(f);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(f != null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// keySet() 으로 key 만 모아줌, value ( 클래스 풀네임) 객체 생성위해 꺼내야함
		Iterator iter = p.keySet().iterator();
		
		// 반복으로 value 꺼내고 객체생성 후 map 에 대입
		while(iter.hasNext()) {
			//key 꺼내서 변수에 대입
			String key = (String)iter.next();

			// key 변수를 사용하여 해당키의 value 를 꺼내고 변수에 대입
			String value = p.getProperty(key);
			
			try {
				Class c = Class.forName(value);	// 예외처리 필수
				
				// 객체 생성 후 에 action 이라는 변수에 대입
				SuperAction action = (SuperAction)c.newInstance();
				
				// map 안에 넣음  - value : 객체
				map.put(key, action);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//service() = doGet() + doPost()
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 uri : properties 의 key 이다
		String uri = request.getRequestURI();
		
		//  모든 Action 클래스들은 SuperAction 클래스를 구현한다
		// 다형성/조상의 타입에 자손의 객체가 대입가능
 		SuperAction sa = map.get(uri);	// init() 메서드에서 대입된 객체 꺼냄
		
 		//action method 를 호출하고 대입
		String view = sa.action(request, response);
		
		// view 이동
		request.getRequestDispatcher(view).forward(request, response);
	}


}
