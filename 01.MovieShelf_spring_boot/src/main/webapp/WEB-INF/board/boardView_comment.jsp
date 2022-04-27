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

        <table class="comments">
            <thead>
            <tr>
                <th width="200px">작성자</th>
                <th width="300px">댓글</th>
                <th width="100px">대댓글</th>
                <th width="80px">수정</th>
                <th width="80px">삭제</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <c:choose>
                        <c:when test="${ comment.depth == 0}">
                            <td><c:out value="${comment.user_id}"/></td>
                            <td><c:out value="${comment.comment_content}"/></td>
                            <c:if test="${log != null}">
                                <td>
                                    <button class="btn btn-primary" onclick="location.href='/board/comment/addPlusComment/${post.talk_no}/${comment.sort_no}/${comment.comment_id}'">
                                        +
                                    </button>
                                </td>
                            </c:if>
                        </c:when>
                        <c:when test="${comment.depth == 1}">
                            &nbsp;&nbsp;&nbsp;&nbsp;<td class="plusCommentArea">ㄴ<c:out value="${comment.user_id}"/></td>
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
            </tbody>
        </table>
        <%-- 로그인 되어있으면 => 댓글달기(자기 게시물도 가능)--%>
        <c:if test="${log != null}">
            <form action="/board/comment/addComment/${post.talk_no}">
                <input type="text" name="comment" id="comment"   placeholder="댓글" required/>
                <input class="btn btn-primary" type="submit" value="댓글달기"/>
            </form>
        </c:if>
    </section>
</main>
<c:import url="../footer_.jsp"></c:import>
