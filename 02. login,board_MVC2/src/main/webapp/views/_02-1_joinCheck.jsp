<%@page import="model.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	UserDTO user = (UserDTO)request.getAttribute("user");
	if(user != null){%>
		<p>찾으신 '<%= user.getId() %>'의 비밀번호는 '<%= user.getPw() %>' 입니다 </p>
	<%}else { %>
		<p>없는 아이디 입니다</p>
	<%} %>
	<button onclick="location.href='service?command=loginForm'">로그인 창으로 이동 </button>
</body>
</html>