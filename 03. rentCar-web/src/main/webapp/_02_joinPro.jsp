<%@page import="javax.websocket.SendResult"%>
<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	
</script>
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
		// 패스워드 확인
		if(pw1.equals(pw_check)){ // 패스워드, 재패스워드 확인
			MemberDAO dao = MemberDAO.getInstance();
			if(dao.getOneMember(id) == null){ // 중복아이디X
				int age = Integer.parseInt(ageString);
				MemberDTO member = new MemberDTO(id, pw1, email, tel, hobby, job, age, info);
				if( dao.addMember(member) ){	
					System.out.println("가입성공");
					response.sendRedirect("_01_login.jsp?pass=null&id="+id);							
				}else{ // 예기치 않은 상황
					System.out.println("가입실패");
					response.sendRedirect("_02_join.jsp");			
				}
			}
			else{
				System.out.println("중복아이디");
				response.sendRedirect("_02_join.jsp");			
			}
			
		}else{
			System.out.println("비밀번호 불일치");
			response.sendRedirect("_02_join.jsp");			
		}
	}
	else{
		System.out.println("정보누락");
		response.sendRedirect("_02_join.jsp");			
	}
%>
</body>
</html>