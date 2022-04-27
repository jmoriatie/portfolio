<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="_0_header.jsp"%>

<%
	String noString = request.getParameter("no");

	int no = Integer.parseInt(noString);
	System.out.println("delNumber: " + no);
	%>
<main>
	<section class="sec1">
		<div class="component">
	
		<h1>비밀번호 입력</h1>
		<form method="post" action="_5-8_boardDeletePro.jsp?no=<%=no%>">
			<span>pw:&#9;</span><input type="text" name="pw"><br> <br>
			<input type="submit" value="삭제">
		</form>
		</div>
	</section>
</main>
<%@ include file="_0_footer.jsp"%>