<%@page import="model.dto.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    <style>
        span{
            white-space: pre;
        }
    </style>
    
    <title>Index</title>
</head>

<body>
    	<%
/*     	UserDAO dao = UserDAO.getInstance();
    	
    	ArrayList<UserDTO> datas = dao.getUsers();
    	
    	for(int i=0; i<datas.size(); i++){
    		UserDTO user = datas.get(i);
    		System.out.println(String.format("%s/%s/%s", user.getId(), user.getPw(), user.getRegDate()));
    	}  */                           
    	%>
    <h1>Index Page : MVC2 디벨롭</h1>
	<button onclick="location.href='service?command=loginForm'">로그인</button>
	<button onclick="location.href='service?command=joinForm'">회원가입</button>
 
</body>

</html>