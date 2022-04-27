package controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "";
		
		// false 로 설정시
		// ㄴ session 값이 있으면, 해당 값을 가져오며,
		// ㄴ 없을 시 null을 반환
		// (true가 default이며, 해당 값은 session이 없으면, 새로 만들어서 반환)
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.removeAttribute("log");
			System.out.println("로그아웃!");
			url = "service?command=index";
		}
		else {
			url = "servce?command=main";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
