<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@page import="reserve.ReserveDAO"%>
<%@page import="reserve.ReserveDTO"%>
<%@page import="rentCar.RentCarDAO"%>
<%@page import="rentCar.RentCarDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="_0_header.jsp"%>

<%
	String id = (String)session.getAttribute("log");
	MemberDTO member = MemberDAO.getInstance().getOneMember(id);
	%>
<main>
	<section class="sec1">
	<div class="component">
	<div>
	<h4>내 정보</h4>
		<table class="myPageTable">
			<tr>
				<th>아이디:</th>
				<td><span><%=member.getId() %></span></td>
			</tr>
			<tr>
				<th>비밀번호:</th>
				<td><span><%=member.getPw1() %></span></td>
			</tr>
			<tr>
				<th>이메일:</th>
				<td><span><%=member.getEmail() %></span></td>
			</tr>
			<tr>
				<th>전화번호:</th>
				<td><span><%=member.getTel() %></span></td>
			</tr>
			<tr>
				<th>취미:</th>
				<td><span><%=member.getHobby() %></span></td>
			</tr>
			<tr>
				<th>직업:</th>
				<td><span><%=member.getJob() %></span></td>
			</tr>
			<tr>
				<th>나이:</th>
				<td><span><%=member.getAge() %>세
				</span></td>
			</tr>
			<tr>
				<th>한줄소개:</th>
				<td><span>><%=member.getInfo() %></span></td>
			</tr>
		</table>
		<button onclick="location.href='_04_memberUpdate.jsp'">수정하기</button>
		<button onclick="location.href='_04-1_memberDelete.jsp'">삭제하기</button>
	</div>
	<br><br>
		<div class="writeList">
		<h4>내가 쓴 글</h4>
			<table class="boardTable">
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>좋아요</th>
			<th>작성일</th>
				<%
            BoardDAO dao = BoardDAO.getInstance();
            ArrayList<BoardDTO> boardList = dao.getBoardList();
            
            // 아이디 같은 같은 게시물 전체 출력
            for(BoardDTO post : boardList){%>
				<% if(post.getId().equals(id)){ %>
				<tr>
					<td><%= post.getNo() %></td>
					<td
						onclick="location.href='_5-4_boardView.jsp?no=<%=post.getNo()%>'"><%= post.getTitle() %></td>
					<td><%= post.getId() %></td>

					<td><%= post.getLikes() %></td>
					<td><%= post.getRegDate() %></td>
				</tr>
				<%} %>
				<%}%>
			</table>
			<button onclick="location.href='_5-2_boardWrite.jsp'">글쓰기</button>
		</div>

		<div class="carListBlock">
		<br><h4>나의 렌트 리스트</h4>
			<table class="carTable">
				<%
    ArrayList<ReserveDTO> rentList = ReserveDAO.getInstance().getReservations();
    for(ReserveDTO reservation : rentList){ %>
				<%if(reservation.getId().equals(id)){
	       	RentCarDTO car = RentCarDAO.getInstance().getOneRentCar(reservation.getNo());
       	 	int cat = car.getCategory(); 
	     %>
				<tr>
					<td class="tdImg"><img src="img/<%=car.getImage() %>"
						onclick="location.href='_6-2_rentCarView.jsp?no=<%=car.getNo()%>'"
						width="150px" height="100px"
						></td>
					<td width="300px">
						<h4
							onclick="location.href='_6-2_rentCarView.jsp?no=<%=car.getNo()%>'"><%=car.getName() %></h4>
						<p>
							회사:
							<%=car.getCompany() %></p>
						<p>
							차종:
							<%=RentCarDAO.getInstance().selectCategory(cat) %></p>
						<p>
							가격:
							<%=car.getPrice()%>원
						</p>
						<p>
							보험 :
							<%= reservation.getUse_in() == 1? "가입":"미가입" %></p>
						<p>
							사용 서비스 :
							<%= reservation.getUse_navi() == 1? "네비게이션 ":""%>
							<%= reservation.getUse_wifi() == 1? "와이파이 ":""%>
							<%= reservation.getUse_seat() == 1? "카시트":""%></p>
						<p>
							예약기간 :
							<%= reservation.getR_day() %></p>
						<p>
							잔여 일 수가
							<%= reservation.getD_day() %>일 남았습니다
						</p>
						<button
							onclick="location.href='_6-5_reserveDeletePro.jsp?reserve_seq=<%=reservation.getReserve_seq()%>'">예약취소</button>
					</td>
				</tr>
				<%} %>
				<%} %>
			</table>
			<button onclick="location.href='_00_index.jsp'">뒤로가기</button>
			</div>
		</div>
	</section>
</main>
<%@ include file="_0_footer.jsp"%>