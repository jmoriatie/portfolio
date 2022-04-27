<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@500&display=swap"
	rel="stylesheet">

<link rel="stylesheet" type="text/css" href="_02_join.css">

<title>렌트카:회원삭제</title>
</head>
<body>
	<%
	String id = (String)session.getAttribute("log");
	MemberDTO deleteMember = MemberDAO.getInstance().getOneMember(id);
%>
	<div id="a">
		<section id="sec1"></section>
		<section id="sec2">
			<article>
				<div class="join_header">
					<span id="title">RENT CAR</span><br>
					<br>
				</div>
				<div class="join_main">
					<form method="POST" action="_04-2_memberDeletePro.jsp">
						<span>아이디</span><br> <input type="text" name="id" id="id"
							value="<%=deleteMember.getId()%>" readonly>
						<p class="message" id="id_message"></p>

						<span>비밀번호</span><br> <input type="password" name="pw1"
							id="pw1" placeholder="비밀번호">
						<p class="message" id="pw_message"></p>

						<span>비밀번호 재확인</span><br> <input type="password"
							name="pw_check" id="pw_check" placeholder="비밀번호 재확인">
						<p class="message" id="pw_check_message"></p>

						<input type="submit" id="submit" value="삭제하기">
					</form>
				</div>
			</article>
		</section>
		<section id="sec3"></section>
	</div>
</body>
</html>