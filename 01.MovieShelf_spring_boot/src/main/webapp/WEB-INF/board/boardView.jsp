<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="log" value="${sessionScope.log}" scope="page"/>
<c:set var="post" value="${requestScope.post}" scope="page"/>
<c:set var="comments" value="${requestScope.comments}" scope="page"/>
<div class="small text-muted">MovieShelf</div>
<h2>게시글</h2>
<hr>

<img src="${post.movie_poster}"/>

<table class="table table--block--boardView">
    <thead>
    <tr>
        <th>영화명</th>
        <th>제목</th>
        <th>작성자</th>
        <th>좋아요 수</th>
        <th>작성일</th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td><c:out value="${post.movie_name}"/></td>
        <td><c:out value="${post.talk_title}"/></td>
        <td><c:out value="${post.user_id}"/></td>
        <td><c:out value="${post.talk_likes}"/></td>
        <td><c:out value="${post.talk_regdate}"/></td>
    </tr>
    </tbody>
</table>
<p id="contentArea"><c:out value="${post.talk_content}"/></p>
<hr>

<%-- 로그인 되어있지만 아이디 다르면 => 좋아요(자기게시물 좋아요 불가)--%>
<c:if test="${log != null && !log.user_id.equals(post.user_id)}">
    <button class="btn btn-primary"
            onclick="location.href='${pageContext.request.contextPath}/board/${post.talk_no}/increaseLike'">
        좋아요+1
    </button>

</c:if>
<%-- 로그인 되어있고 아이디 같으면 => 수정/삭제--%>
<c:if test="${log != null && log.user_id.equals(post.user_id)}">
    <button class="btn btn-primary"
            onclick="location.href='${pageContext.request.contextPath}/boardUpdateForm/${post.talk_no}'">게시글
        수정
    </button>
    <button class="btn btn-primary"
            onclick="location.href='${pageContext.request.contextPath}/boardDelete/${post.talk_no}'">게시글
        삭제
    </button>
</c:if>

<hr>