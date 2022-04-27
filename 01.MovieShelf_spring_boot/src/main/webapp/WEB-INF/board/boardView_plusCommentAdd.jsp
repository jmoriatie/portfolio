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
        <c:import url="boardView.jsp"/>
        <c:set var="plusComment" value="${requestScope.plusComment}" scope="page"/>

        <table class="comments">
            <tr>
                <td width="200px">작성자</td>
                <td width="300px">댓글</td>
                <td>대댓글</td>
                <td>수정</td>
                <td>삭제</td>
            </tr>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <c:choose>
                        <c:when test="${log != null && comment.depth == 0}">
                            <td><c:out value="${comment.user_id}"/></td>
                            <td><c:out value="${comment.comment_content}"/></td>
                            <td></td>
                        </c:when>
                        <%-- comment comment_id가 받아온 객체(dto)와 같을 경우 내용 입력받음--%>
                        <c:when test="${log != null && comment.depth == 1 && comment.sort_no == plusComment.sort_no}">
                            &nbsp;<td>ㄴ<c:out value="${comment.user_id}"/></td>
                            <form action="/board/comment/setPlusComment/${post.talk_no}/${comment.comment_id}">
                                <td><input type="text" name="plusComment" id="plusComment"></td>
                                <td><input class="btn btn-primary" type="submit" value="+"></td>
                            </form>
                        </c:when>

                        <c:when test="${log != null && comment.depth == 1 && comment.sort_no != plusComment.sort_no}">
                            &nbsp;<td>ㄴ<c:out value="${comment.user_id}"/></td>
                            <td><c:out value="${comment.comment_content}"/></td>
                            <td></td>
                        </c:when>
                    </c:choose>

                    <c:choose>
                        <c:when test="${log != null && log.user_id.equals(comment.user_id)}">
                            <td>
                                <button class="btn btn-primary" onclick="location.href='/board/${post.talk_no}/updateComment/${comment.comment_id}'">
                                    V
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-primary" onclick="location.href='/board/comment/deleteComment/${comment.comment_id}'">
                                    V
                                </button>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <%-- 로그인 되어있으면 => 댓글달기(자기 게시물도 가능)--%>
        <c:if test="${log != null}">
            <form action="/board/comment/addComment/${post.talk_no}">
                <input type="text" name="comment" id="comment" placeholder="댓글" required/>
                <input class="btn btn-primary" type="submit" value="댓글달기"/>
            </form>
        </c:if>
    </section>
</main>

<script>
    document.getElementById("plusComment").focus();
</script>
<c:import url="../footer_.jsp"></c:import>

