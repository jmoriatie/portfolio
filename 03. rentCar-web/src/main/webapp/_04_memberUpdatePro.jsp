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
request.setCharacterEncoding("utf-8"); 

String id = request.getParameter("id");
String pw1 = request.getParameter("pw1");
String pw_check = request.getParameter("pw_check");
String email = request.getParameter("email");
String tel = request.getParameter("tel");
String hobby = request.getParameter("hobby");
String job = request.getParameter("job");
String info = request.getParameter("info");
String ageString = request.getParameter("age");

System.out.println(ageString);

// 전부 빈칸 아닌 상황
if(!id.equals("") && !pw1.equals("") && !pw_check.equals("") && !email.equals("") && !tel.equals("") && !hobby.equals("") && !job.equals("") && !info.equals("") && !ageString.equals("") ){
	System.out.println();
	// 패스워드 확인
	if(pw1.equals(pw_check)){ // 패스워드, 재패스워드 확인
		MemberDAO dao = MemberDAO.getInstance();
			int age = Integer.parseInt(ageString);
			MemberDTO member = new MemberDTO(id, pw1, email, tel, hobby, job, age, info);
			if( dao.updateMember(member) ){
				System.out.println("업데이트 성공");
				response.sendRedirect("_03_myPage.jsp");							
			}else{ // 예기치 않은 상황
				System.out.println("업데이트 실패");
				response.sendRedirect("_04_memberUpdate.jsp");			
			}
	}else{
		System.out.println("비밀번호 불일치");
		response.sendRedirect("_04_memberUpdate.jsp");			
	}
}
else{
	System.out.println("정보누락");
	response.sendRedirect("_04_memberUpdate.jsp");			
}
%>
</body>
</html>