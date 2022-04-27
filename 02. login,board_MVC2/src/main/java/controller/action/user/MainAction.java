package controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;

public class MainAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		String url = "";
		
		// 혹시 세션이 없거나, log값이 없으면 index페이지로 이동 
		if(session == null || session.getAttribute("log") == null) {
			url = "service?command=index";
		}else {
			System.out.println("session: " + session.getAttribute("log"));
			url = "views/_01_main.jsp";
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);		
	}

}
