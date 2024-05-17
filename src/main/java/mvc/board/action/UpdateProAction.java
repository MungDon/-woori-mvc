package mvc.board.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.board.model.BoardDAO;
import mvc.board.model.BoardDTO;
import mvc.test.action.SuperAction;

public class UpdateProAction implements SuperAction {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String filePath = request.getRealPath("resources/upload");// 업로드할 실제 폴더 경로
		int max = 1024 * 1024 * 5; // 파일크기 설정
		String enc = "UTF-8"; // 인코딩
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();// 같은파일이 있으면 이름 옆에 숫자를 붙임
		try {
			MultipartRequest mr = new MultipartRequest(request, filePath, max, enc, dp);
			String orgImg = mr.getParameter("orgimg");
			String img = mr.getFilesystemName("img");
			String pageNum = request.getParameter("pageNum");
			
			BoardDAO dao = BoardDAO.getInstance();
			BoardDTO dto = new BoardDTO();
			
			dto.setNum(Integer.parseInt(mr.getParameter("num")));
			dto.setWriter(mr.getParameter("writer"));
			dto.setTitle(mr.getParameter("title"));
			dto.setContent(mr.getParameter("content"));
			
			if(mr.getParameter("passwd").equals("")) {
				dto.setPasswd("none");
			}else {
				dto.setPasswd(mr.getParameter("passwd"));
			}
			
			
			
			dto.setImg(img);	 // 기존이미지 없을때 이미지 삽입
			
			if (orgImg != null && img == null) {// 기존이미지는 있으나 수정안할때
				dto.setImg(orgImg);
			}
			if (orgImg != null && img != null) {// 기존이미지는 있으나 수정할때
				File f = new File(filePath +"/"+ orgImg); // 리얼패스에 있는 원본이미지를 읽음
				if (f != null) { // 있다면
					f.delete(); // 삭제
				}
				dto.setImg(img);// 수정한 이미지 저장
			} 
		
			int result = dao.boardUpPro(dto);

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "/WEB-INF/views/board/updatePro.jsp";
	}

}
