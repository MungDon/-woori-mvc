package mvc.board.action;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.model.BoardDAO;
import mvc.test.action.SuperAction;

public class DeleteProAction implements SuperAction{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int result =0;
		String passwd ="";
		String filePath = request.getRealPath("resources/upload");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		if(request.getParameter("passwd").equals("")) {
			passwd="none";
		}else {
			passwd = request.getParameter("passwd");
		}
		BoardDAO dao = BoardDAO.getInstance();
		
		
		String img = dao.findByImg(num);
		result = dao.boardDelete(num, passwd);
	
		
		if(result==1 && !(img.equals(""))) {
			File f = new File(filePath+"/"+img);
				f.delete();
		}


		 
		 request.setAttribute("pageNum", pageNum);
		 request.setAttribute("result", result);
		return "/WEB-INF/views/board/deletePro.jsp";
	}

}
