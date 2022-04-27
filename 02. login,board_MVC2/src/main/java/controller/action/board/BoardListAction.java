package controller.action.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class BoardListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 보드 리스트 넘겨주기
		ArrayList<BoardDTO> boardList = BoardDAO.getInstance().getBoardList();
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("views/_04_boardList.jsp").forward(request, response);
			
	}

}
