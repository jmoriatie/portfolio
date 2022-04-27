package controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;
import model.dao.BoardDAO;
import model.dto.BoardDTO;
import model.dto.UserDTO;

public class BoardDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = BoardDAO.getInstance();		

		String noString = request.getParameter("no");
		int no = Integer.parseInt(noString);
		System.out.println("[게시물 삭제]\n번호: " + no);
		
		HttpSession session = request.getSession(false); 
		UserDTO user = (UserDTO)session.getAttribute("log");
		String id = user.getId();
				
		// 비교당할 데이터(DTO 객체)
		BoardDTO post = dao.getOnePost(no);

		String url = "";
		if(post.getId().equals(id)){ 
			if(dao.deleteBoard(no)){ 
				url = "service?command=boardList";
			}
			else {
				url = "service?command=boardView&no="+no;
			}
		}
		else{
			url = "service?command=boardView&no="+no;
		}
		
		response.sendRedirect(url);
	}

}
