<%@page import="reserve.ReserveDAO"%>
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
	int reserve_seq = Integer.parseInt( request.getParameter("reserve_seq") ); 
	
	// 해당 예약 삭제
	ReserveDAO.getInstance().deleteOneReservation(reserve_seq);

	response.sendRedirect("_03_myPage.jsp");
%>

</body>
</html>