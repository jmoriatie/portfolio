<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
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
	String noString = request.getParameter("no");
	
	int no = Integer.parseInt(noString);
	System.out.println("likePostNum: "  + no);
	
	BoardDTO post = BoardDAO.getInstance().getOnePost(no);

	BoardDAO.getInstance().plusLike(post);
	
 	response.sendRedirect("_5-4_boardView.jsp?no="+noString);
%>
</body>
</html>