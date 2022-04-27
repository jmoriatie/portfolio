<%@page import="reserve.ReserveDTO"%>
<%@page import="reserve.ReserveDAO"%>
<%@page import="rentCar.RentCarDAO"%>
<%@page import="rentCar.RentCarDTO"%>
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
	String id = (String)session.getAttribute("log");
	String noStr = request.getParameter("no");
	int no = Integer.parseInt(noStr);
	
	RentCarDTO car = RentCarDAO.getInstance().getOneRentCar(no);
	
	ReserveDAO dao = ReserveDAO.getInstance();
	
	String r_day = request.getParameter("r_day");
	String use_in = request.getParameter("use_in");
	String use_wifi = request.getParameter("use_wifi");
	String use_navi = request.getParameter("use_navi");
	String use_seat = request.getParameter("use_seat");
	int qty = Integer.parseInt(request.getParameter("qty"));
	
	ReserveDTO reservation = new ReserveDTO(no, id, qty, r_day, dao.checkToInt(use_in), dao.checkToInt(use_wifi), dao.checkToInt(use_navi), dao.checkToInt(use_seat)); 
	System.out.println("차량번호:"+car.getName()+"/예약차량:"+car.getName()+"/예약자:"+id+"/예약일:" + r_day + "/보험여부:" + use_in + "/와이파이:" + use_wifi + "/예약차량수:" + qty + "");
	

	if(qty < 1 || r_day.equals("")){
		System.out.println("예약날짜 입력!! 또는 차대여 음수 확인!!!");
		response.sendRedirect("_6-3_reserve.jsp?no="+noStr);
	}
	else if(dao.addReservation(reservation)){
 		response.sendRedirect("_00_index.jsp");
	}else{
		response.sendRedirect("_6-3_reserve.jsp?no="+noStr);
	}
%>
</body>
</html>