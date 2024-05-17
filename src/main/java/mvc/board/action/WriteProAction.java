package mvc.board.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
			String filePath = request.getRealPath("resources/upload");// 업로드할 실제 폴더 경로
			int max = 1024*1024*5; // 파일크기 설정
			String enc = "UTF-8";	// 인코딩
			DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();// 같은파일이 있으면 이름 옆에 숫자를 붙임
			try {
				MultipartRequest mr = new MultipartRequest(request, filePath,max,enc,dp);
				
				BoardDAO dao = BoardDAO.getInstance();
				BoardDTO dto = new BoardDTO();
				dto.setNum(Integer.parseInt(mr.getParameter("num")));
				dto.setRef(Integer.parseInt(mr.getParameter("ref")));
				dto.setRe_step(Integer.parseInt(mr.getParameter("re_step")));
				dto.setRe_level(Integer.parseInt(mr.getParameter("re_level")));
				dto.setWriter(mr.getParameter("writer"));
				dto.setTitle(mr.getParameter("title"));
				dto.setContent(mr.getParameter("content"));
				if(mr.getParameter("passwd").equals("")) {
					dto.setPasswd("none");
				}else {
					dto.setPasswd(mr.getParameter("passwd"));
				}
				dto.setImg(mr.getFilesystemName("img"));
		
				int result = dao.boardInsert(dto);
				
				request.setAttribute("result", result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
			
			
		   return "/WEB-INF/views/board/writePro.jsp";
	}

}
