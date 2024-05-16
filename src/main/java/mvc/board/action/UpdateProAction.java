package mvc.board.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.model.BoardDAO;
import mvc.board.model.BoardDTO;
import mvc.test.action.SuperAction;

public class UpdateProAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		String pageNum = request.getParameter("pageNum");
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setPasswd(request.getParameter("passwd"));
		int result = dao.boardUpPro(dto);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		return "/WEB-INF/views/board/updatePro.jsp";
	}

}
