package mvc.board.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.model.BoardDAO;
import mvc.board.model.BoardDTO;
import mvc.test.action.SuperAction;

public class WriteProAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		
			BoardDAO dao = BoardDAO.getInstance();
			BoardDTO dto = new BoardDTO();
			dto.setNum(Integer.parseInt(request.getParameter("num")));
			dto.setRef(Integer.parseInt(request.getParameter("ref")));
			dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
			dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
			dto.setWriter(request.getParameter("writer"));
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			dto.setPasswd(request.getParameter("passwd"));
			int result = dao.boardInsert(dto);
			
			request.setAttribute("result", result);
		   return "/WEB-INF/views/board/writePro.jsp";
	}

}
