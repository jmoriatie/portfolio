<%@page import="board.BoardDAO"%>
<%@page import="reserve.ReserveDAO"%>
<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <title>렌트카:회원삭제</title>
</head>

<body>

<%

request.setCharacterEncoding("utf-8"); 

String id = request.getParameter("id");
String pw1 = request.getParameter("pw1");
String pw_check = request.getParameter("pw_check");

if(!id.equals("") && !pw1.equals("") && !pw_check.equals("") ){
	// 패스워드 확인
	if(pw1.equals(pw_check)){ // 패스워드, 재패스워드 확인
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO delMember = dao.getOneMember(id);
			// 실제 비밀번호가 맞다면
			if(pw1.equals(delMember.getPw1())){
				if( dao.deleteMember(delMember) ){
					BoardDAO.getInstance().deleteAll(id); // 해당 게시글 전체 삭제
					ReserveDAO.getInstance().deleteAllReservation(id); // 렌트내용 전부 삭제
					
					System.out.println("삭제 성공");
					session.removeAttribute("log");
					response.sendRedirect("_00_index.jsp");							
				}else{ // 예기치 않은 상황
					System.out.println("업데이트 실패");
					response.sendRedirect("_04-1_memberDelete.jsp");			
				}
			}else{
				System.out.println("비밀번호 틀림");
				response.sendRedirect("_04-1_memberDelete.jsp");		
			}
	}else{
		System.out.println("비밀번호 불일치");
		response.sendRedirect("_04-1_memberDelete.jsp");			
	}
}
else{
	System.out.println("정보누락");
	response.sendRedirect("_04-1_memberDelete.jsp");			
}
%>
</body>

</html>