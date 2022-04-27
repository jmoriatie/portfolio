<%@page import="rentCar.RentCarDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="rentCar.RentCarDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="_0_header.jsp"%>
<%
String search = request.getParameter("search");

RentCarDAO dao = RentCarDAO.getInstance();
ArrayList<RentCarDTO> rentCars = dao.getRentCars();
%>
<main>
	<section class="sec1">
		<div class="component">
	
		<h1>
			렌트카 검색 : "<%=search%>"
		</h1>
			<table class="carTable">
				<%
				int num = 0;
				for (RentCarDTO car : rentCars) {
					if (car.getName().contains(search)) {
						num++;
				%>
				<%
				int cat = car.getCategory();
				%>
				<tr>
					<td><img src="img/<%=car.getImage()%>" width="150px"
						height="100px"
						onclick="location.href='_6-2_rentCarView.jsp?no=<%=car.getNo()%>'"></td>
					<td width="300px">
						<h4
							onclick="location.href='_6-2_rentCarView.jsp?no=<%=car.getNo()%>'"><%=car.getName()%></h4>
						<p>
							회사:
							<%=car.getCompany()%></p>
						<p>
							차종:
							<%=RentCarDAO.getInstance().selectCategory(cat)%></p>
						<p>
							가격:
							<%=car.getPrice()%>원
						</p>
					</td>
				</tr>
				<%
				}
				}
				%>

			</table>


		<%
		if (num != 0) {
		%>
		<p><%=num%>개의 검색 결과가 있습니다
		</p>
		<%
		} else {
		%>
		<p>검색결과가 없습니다</p>
		<%
		}
		%>
		<button onclick="location.href='_00_index.jsp'">뒤로가기</button>
	</div>
	</section>
</main>
<%@ include file="_0_footer.jsp"%>