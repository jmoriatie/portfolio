package controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class BoardViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String noString = request.getParameter("no");
		int no = Integer.parseInt(noString);
		
		System.out.println("게시물 번호: " + no);

		BoardDTO post = BoardDAO.getInstance().getOnePost(no);
		
		request.setAttribute("post", post);
		request.getRequestDispatcher("views/_06_boardView.jsp").forward(request, response);
	}

}
