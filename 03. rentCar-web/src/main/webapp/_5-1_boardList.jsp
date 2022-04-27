<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="_0_header.jsp" %>

<%
	String id = (String)session.getAttribute("log");
%>
<main>
    <section class="sec1">
    <article>
	<div class="component">
	<form action="_5-0_boardSearchList.jsp"  class="searchForm">
		<span>제목검색:&nbsp; </span><input type="text" name="search" id="search" placeholder="검색">
		<input type="submit" value="검색">
	</form>
	<h1>게시판</h1>
		<table class="boardTable">
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>좋아요</th>
			<th>작성일</th>
			
			<%
            BoardDAO dao = BoardDAO.getInstance();
            ArrayList<BoardDTO> boardList = dao.getBoardList();
            
            for(BoardDTO board : boardList){%>
			<tr>
				<td><%= board.getNo() %></td>
				<td onclick="location.href='_5-4_boardView.jsp?no=<%=board.getNo()%>'"><%= board.getTitle() %></td>
				<td><%= board.getId() %></td>
				<td><%= board.getLikes() %></td>
				<td><%= board.getRegDate() %></td>
			</tr>
			<% 	
            }%>
		</table>
		<%if(id != null){%>
			<button onclick="location.href='_5-2_boardWrite.jsp'">글쓰기</button>		
		<%}%>
		<button onclick="location.href='_00_index.jsp'">뒤로가기</button>
	</div>
	</article>
</section>
</main>
<%@ include file="_0_footer.jsp" %>