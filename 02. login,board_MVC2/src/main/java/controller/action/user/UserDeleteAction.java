package controller.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;
import model.dao.BoardDAO;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class UserDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			UserDTO user = (UserDTO)session.getAttribute("log");
			if(UserDAO.getInstance().deletelUser(user.getId(), user.getPw())) { // 삭제 성공
				System.out.println("[유저삭제]\nid:" + user.getId());
				BoardDAO.getInstance().deleteAll(user.getId()); // 해당 아이디가 작성한 게시판 전체 삭제
				session.removeAttribute("log"); // 로그인 된 세션 빼고
				url = "service?command=index";
			}
			else { // 삭제 실패
				url = "service?command=main";
			}
		}
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}
}
