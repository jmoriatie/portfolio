<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="_0_header.jsp"%>

<%
String id = (String) session.getAttribute("log");
%>
<main>
	<section class="sec1">
		<div class="component">
	
		<h1>글작성</h1>
		<form action="_5-3_boardWritePro.jsp">
			<table border=1px;>
				<tr>
					<td>제목</td>
					<td><input type="text" id="title" name="title"
						placeholder="제목" style="width: 300px"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea id="content" name="content" placeholder="내용"
							style="overflow: scroll; width: 300px; height: 300px;"></textarea>
					</td>
				</tr>
			</table>
			<%
			if (id == null) {
			%>
			<p>우선 로그인하세요</p>
			<%
			}
			%>
			<input type="submit" value="글저장">
			<button id="back">뒤로가기</button>
			<!-- script -->

		</form>
		</div>
	</section>
</main>

<script>
    document.querySelector('#back').addEventListener('click', e =>{
        e.preventDefault();
        location.href='_5-1_boardList.jsp';
    });

</script>

<%@ include file="_0_footer.jsp"%>