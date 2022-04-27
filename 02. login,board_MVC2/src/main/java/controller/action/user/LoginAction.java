package controller.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class LoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		
		String id = request.getParameter("id");		
		String pw = request.getParameter("pw");		

		UserDTO user = UserDAO.getInstance().getOneUser(id);
		
		// 완료 하면 main page로 이동
		if(user != null && user.getPw().equals(pw)) {
			// HttpSession
			// ㄴ request.getSession() : 파라미터 true가 default
			// 		ㄴ true : session이 없으면, 새로 만들어서 반환
			//		ㄴ false : session이 없으면 null을 반환
			HttpSession session = request.getSession();
			session.setAttribute("log", user);
			
			UserDTO dto = (UserDTO)session.getAttribute("log");
			System.out.println("[로그인]\nid: " + dto.getId()+", pw: "+dto.getPw());
			
			url = "service?command=main";
		}
		else {
			url = "service?command=loginForm";
			System.out.println("아이디 또는 비밀번호 확인");
		}
		RequestDispatcher dpt = request.getRequestDispatcher(url);
		dpt.forward(request, response);
	}

}
