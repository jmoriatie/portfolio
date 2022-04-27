<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="_0_header.jsp"%>

<!-- 누르면 보이는 상세페이지 -->
<%
	String noString = request.getParameter("no");
	int no = Integer.parseInt(noString);

	BoardDAO dao = BoardDAO.getInstance();
	BoardDTO post = dao.getOnePost(no);

	String id = (String) session.getAttribute("log");
	
	ArrayList<String> comments = dao.printComments(no); // 댓글 불러오기
	%>
<main>
	<section class="sec1">
		<div class="component">
			<h1>게시글</h1>
			<table class="boardTable">
				<tr>
					<th>제목</th>
					<td colspan="3"><span id="title" name="title"
						style="width: 300px"><%= post.getTitle() %></span></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><span id="id" name="id" style="width: 300px"><%= post.getId() %></span>
					</td>
					<td>작성일</td>
					<td><span id="regDate" name="regDate" style="width: 300px"><%= post.getRegDate() %></span>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"><span id="content" name="content"
						style="overflow: scroll; width: 300px; height: 300px;"><%= post.getContents() %></span>
					</td>
				</tr>
				<tr>
					<th>좋아요</th>
					<td colspan="3"><span id="likes" name="likes"
						style="width: 300px; height: 300px;"><%= post.getLikes() %>개</span>
					</td>
				</tr>
			</table>

			<!-- 댓글 보는 란, 적는 란 -->
			<div class="commentForm">
			<% if(id != null){ %>
			<form action="_5-9_commentsPro.jsp">
				<input type="hidden" name="no" value="<%=no %>"> 
				<input type="text" name="comment" id="comment"> 
				<input type="submit" value="댓글작성" class="ok">
			</form>
			<%} %>
			</div >
			<% if(comments != null && comments.size() != 0){ %>
			<table class="commentTable">
				<tr>
					<th class="commentTitle">작성자</th>				
					<th class="commentTitle">내용</th>
					<th class="commentTitle">삭제</th>				
									
				</tr>
				<% for(int i=0; i<comments.size()-1; i+=2){ %>
				<tr>
					<% String cmt = comments.get(i)+"="+comments.get(i+1); %>
					<td class="commentContent"><%= comments.get(i) %></td>
					<td class="commentContent"><%= comments.get(i+1) %></td>
					<td class="commentContent">
					<%if(id != null && id.equals(comments.get(i))){ %>
					<button onclick="location.href='_5-9_commentDeletePro.jsp?no=<%=no%>&cmt=<%=cmt%>'">삭제하기</button>
					<%} %>
					</td>
				</tr>
				<%} %>
				<%} %>
			</table>
			<%if(id != null && id.equals( post.getId() )){%>
			<!-- 내 게시물: 수정, 삭제 노출 -->
			<button id="modify"
				onclick="location.href='_5-5_boardUpdate.jsp?no=<%=no%>'">글수정</button>
			<button id="delete"
				onclick="location.href='_5-7_boardDelete.jsp?no=<%=no%>'">글삭제</button>
			<%}else{ %>
			<!-- 내 게시물 아닐때 : 좋아요 노출 -->
			<button id="likes"
				onclick="location.href='_5-9_likesPro.jsp?no=<%=no%>'">좋아요</button>
			<%} %>
			<button id="back" onclick="location.href='_5-1_boardList.jsp'">뒤로가기</button>
		</div>
	</section>
</main>

<%@ include file="_0_footer.jsp"%>"
