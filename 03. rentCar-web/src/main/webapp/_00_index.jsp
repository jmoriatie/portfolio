<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="rentCar.RentCarDAO"%>
<%@page import="member.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.MemberDAO"%>

<!-- ㄴ header, footer처럼 반복되는 것을 다 복붙하지 않고 include로 사용 가능
	  ㄴ nav도 넣어놓고 사용하면 꿀
	  ㄴ CSS도 헤더에 먹여놓으니까 작동하는 듯 => gird도 적용되는 것이 중요!!! -->

<%
MemberDAO dao = MemberDAO.getInstance();

ArrayList<MemberDTO> datas = dao.getMembers();

for (int i = 0; i < datas.size(); i++) {
	MemberDTO user = datas.get(i);
	System.out.println(user.toString());
}
%>
<%@include file="_0_header.jsp"%>
<main>
	<section class="sec1">
		<article id="main_art1">
			<div class="component">
				
				<div>
					<h4 class="info_title"
						onclick="location.href='_6-1_rentCarList.jsp'">대여가능 차량</h4>
					<table class="showThreeCarTabel">
						<tr>
							<td><img
								src="img/<%=RentCarDAO.getInstance().getOneRentCar(1).getImage()%>"
								width="150px" height="100px"
								onclick="location.href='_6-2_rentCarView.jsp?no=1'">
								<h4 onclick="location.href='_6-2_rentCarView.jsp?no=1'"><%=RentCarDAO.getInstance().getOneRentCar(1).getName()%></h4>
								<p><%=RentCarDAO.getInstance().getOneRentCar(1).getCompany()%></p>
								<p><%=RentCarDAO.getInstance().getOneRentCar(1).getPrice()%>원</p>
							</td>
							<td><img
								src="img/<%=RentCarDAO.getInstance().getOneRentCar(2).getImage()%>"
								width="150px" height="100px"
								onclick="location.href='_6-2_rentCarView.jsp?no=2'">
								<h4 onclick="location.href='_6-2_rentCarView.jsp?no=2'"><%=RentCarDAO.getInstance().getOneRentCar(2).getName()%></h4>
								<p><%=RentCarDAO.getInstance().getOneRentCar(2).getCompany()%></p>
								<p><%=RentCarDAO.getInstance().getOneRentCar(2).getPrice()%>원</p>
							</td>
							<td><img
								src="img/<%=RentCarDAO.getInstance().getOneRentCar(3).getImage()%>"
								width="150px" height="100px"
								onclick="location.href='_6-2_rentCarView.jsp?no=3'">
								<h4 onclick="location.href='_6-2_rentCarView.jsp?no=3'"><%=RentCarDAO.getInstance().getOneRentCar(3).getName()%></h4>
								<p><%=RentCarDAO.getInstance().getOneRentCar(3).getCompany()%></p>
								<p><%=RentCarDAO.getInstance().getOneRentCar(3).getPrice()%>원
								</p></td>
						</tr>
					</table>
					<button id="moreBtn" onclick="location.href='_6-1_rentCarList.jsp'">더보기</button>
				</div>
				<br><br>
				<div>
					<h4>게시판</h4>
					<table class="boardTable">
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>좋아요</th>
						<th>작성일</th>

						<%
						BoardDAO bDao = BoardDAO.getInstance();
						ArrayList<BoardDTO> boardList = bDao.getBoardList();

						int cnt = boardList.size() < 3 ? boardList.size() : 3;
						for (int i = 0; i < cnt; i++) {
						%>
						<tr>
							<td><%=boardList.get(i).getNo()%></td>
							<td
								onclick="location.href='_5-4_boardView.jsp?no=<%=boardList.get(i).getNo()%>'"><%=boardList.get(i).getTitle()%></td>
							<td><%=boardList.get(i).getId()%></td>
							<td><%=boardList.get(i).getLikes()%></td>
							<td><%=boardList.get(i).getRegDate()%></td>
						</tr>
						<%
						}
						%>
					</table>
					<button id="moreBtn" onclick="location.href='_5-1_boardList.jsp'">더보기</button>
					
				</div>

			</div>
		</article>
	</section>

</main>
<aside>어사이드</aside>
<!-- footer도 마찬가지 include로 처리 -->
<%@ include file="_0_footer.jsp"%>