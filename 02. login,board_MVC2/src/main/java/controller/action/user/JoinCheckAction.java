package controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class JoinCheckAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		UserDTO findUser = UserDAO.getInstance().getOneUser(id);  
		request.setAttribute("user", findUser);
		
		// id 있는 유저면 pw 돌려주기
		if(findUser != null) System.out.println("[회원조회]\nid: "+findUser.getId()+", pw: "+ findUser.getPw());			
		else System.out.println("없는 아이디");
	
		request.getRequestDispatcher("views/_02-1_joinCheck.jsp").forward(request, response);
	}
}
