<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="../header_.jsp"></c:import>
<!-- Page header with logo and tagline-->
<header class="py-5 bg-light border-bottom mb-4">
    <div class="container">
        <div class="text-center my-5">
            <h1 class="fw-bolder">나만의 영화 선반</h1>
            <p class="lead mb-0">Movie Shelf</p>
        </div>
    </div>
</header>
<main>
    <section>
        <div class="small text-muted">MovieShelf</div>
        <h2>게시글 수정</h2>
        <hr>
        <form method="post" action="${pageContext.request.contextPath}/boardUpdate/${post.talk_no}">
            <input type="hidden">
            <c:set var="post" value="${requestScope.post}" scope="request"/>

            <table class="type22">
                <tbody>
                <tr>
                    <td rowspan="4">
                        <img src="${post.movie_poster}"/>
                    </td>
                </tr>
                <tr>
                    <th>영화명 :</th>
                    <td colspan="3">
                        <span id="movieText"><c:out value="${post.movie_name}"/></span>
                    </td>
                </tr>
                <tr>
                    <td>제목</td>
                    <td>
                        <input name="title" id="title" type="text" value="${post.talk_title}" required/>
                    </td>
                    <td>작성자</td>
                    <td><c:out value="${post.user_id}"/></td>
                </tr>
                <tr>
                    <td>좋아요 수</td>
                    <td><c:out value="${post.talk_likes}"/></td>
                    <td>작성일</td>
                    <td><c:out value="${post.talk_regdate}"/></td>
                </tr>
                <tr>
                    <td height="500px" colspan="4">
                            <textarea height="500px" name="content" id="content" required>
                                <c:out value="${post.talk_content}"/>
                            </textarea>
                    </td>
                </tr>
                <td>
                    <span>post password: </span><input type="password" name="pw" id="pw">
                </td>
                </tr>
            </table>
            <%--                로그인 객체와 보드 패스워드 비교--%>
            <%--                <input type="button" onclick=""/>--%>
            <input type="submit" value="수정하기">
        </form>
    </section>
</main>
<c:import url="../footer_.jsp"></c:import>