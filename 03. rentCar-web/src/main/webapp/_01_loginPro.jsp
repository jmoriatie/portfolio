<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
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
	// 세션 저장, 아이디, 
	// 비번 없을 시 1 전송=>_01_login.jsp 에서 판단
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String remember = request.getParameter("remember");
	
	System.out.println(remember); // 어떤 형태로 들어오는지
	
	// 데이터베이스 비교 추가 
	if(!id.equals("") && !pw.equals("")){
		// 로그인 성공
		MemberDTO member = MemberDAO.getInstance().getOneMember(id);
		if(member != null && member.getId().equals(id) && member.getPw1().equals(pw)){
		 	session.setAttribute("log", id);
			System.out.println("로그인 성공!");		
			response.sendRedirect("_00_index.jsp");
		}else{
			System.out.println("빈칸");
			response.sendRedirect("_01_login.jsp?pass=2");
		}
/* 		if(remember != null && remember.equals("on")){	
			// 쿠키셋팅
		} */
	}
	else{
		// 실패
		System.out.println("빈칸");
		response.sendRedirect("_01_login.jsp?pass=1");
	}
	
%>
</body>
</html>