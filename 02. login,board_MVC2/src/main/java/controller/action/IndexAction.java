package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.dto.UserDTO;

public class IndexAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	ArrayList<UserDTO> datas = UserDAO.getInstance().getUsers();
    	
    	for(int i=0; i<datas.size(); i++){
    		UserDTO user = datas.get(i);
    		System.out.println(String.format( user.toString() ));
    	} 
		
		request.getRequestDispatcher("_1_index.jsp").forward(request, response);
	}

}
