<%@page import="rentCar.RentCarDAO"%>
<%@page import="rentCar.RentCarDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="_0_header.jsp"%>

<%
	ArrayList<RentCarDTO> rentCars = RentCarDAO.getInstance().getRentCars();
%>
<main>
	<section class="sec1">
	<article>
		<div class="component">
	
		<form class="searchForm" action="_6-0_rentCarSearchList.jsp">
			<span>이름검색:</span>	<input type="text" name="search" id="search" placeholder="검색">
			<input type="submit" value="검색">
		</form>
		<h1>렌트카 리스트</h1>
		<div class="rentCarList">
			<table border="1px" class="carTable">
				<%for(RentCarDTO car : rentCars){ %>
				<%int cat = car.getCategory(); %>
				<tr class="carListBlock">
					<td class="tdImg"><img src="img/<%=car.getImage() %>" width="150px"
						height="100px"
						onclick="location.href='_6-2_rentCarView.jsp?no=<%=car.getNo()%>'">
					</td>
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
					</td>
				</tr>
				<%} %>
			</table>
		</div>
		<button onclick="location.href='_00_index.jsp'">뒤로가기</button>
		</div>
	</article>
	</section>
</main>

<%@ include file="_0_footer.jsp"%>