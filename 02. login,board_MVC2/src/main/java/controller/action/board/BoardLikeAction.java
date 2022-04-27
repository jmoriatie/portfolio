package controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class BoardLikeAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = BoardDAO.getInstance(); 

		String noString = request.getParameter("no");
		int no = Integer.parseInt(noString);
		
		System.out.println("[좋아요]게시물 번호: "  + no);
		
		BoardDTO post = dao.getOnePost(no);

		dao.plusLike(post);

	 	response.sendRedirect("service?command=boardView&no="+no);		
	}

}
