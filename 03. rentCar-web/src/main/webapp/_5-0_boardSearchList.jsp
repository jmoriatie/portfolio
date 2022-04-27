<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="_0_header.jsp"%>

<%
String search = request.getParameter("search");

BoardDAO dao = BoardDAO.getInstance();
ArrayList<BoardDTO> boardList = dao.getBoardList();
%>
<main>
	<section class="sec1">
		<div class="component">

		<form action="_5-0_boardSearchList.jsp">
			<p>제목검색:</p>
			<input type="text" name="search" id="search" placeholder="검색">
			<input type="submit">
		</form>
		<h1>
			게시판 검색: "<%=search%>"
		</h1>
			<table class="boardTable">
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>좋아요</th>
			<th>작성일</th>
				<%
				for (BoardDTO board : boardList) {
					if (board.getTitle().contains(search)) {
				%>
				<tr>
					<td><%=board.getNo()%></td>
					<td
						onclick="location.href='_5-4_boardView.jsp?no=<%=board.getNo()%>'"><%=board.getTitle()%></td>
					<td><%=board.getId()%></td>

					<td><%=board.getLikes()%></td>
					<td><%=board.getRegDate()%></td>
				</tr>
				<%
				}
				}
				%>
			</table>
			<button onclick="location.href='_5-2_boardWrite.jsp'">글쓰기</button>
			<button onclick="location.href='_00_index.jsp'">뒤로가기</button>
		</div>
	</section>
</main>
<%@ include file="_0_footer.jsp"%>