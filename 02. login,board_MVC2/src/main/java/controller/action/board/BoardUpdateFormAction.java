package controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class BoardUpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noString = request.getParameter("no");
		int no = Integer.parseInt(noString);
		
		BoardDTO post = BoardDAO.getInstance().getOnePost(no);
		
		String url = "views/_07_boardUpdate.jsp";
		
		request.setAttribute("post", post);
		request.getRequestDispatcher(url).forward(request, response);		
	}
}
