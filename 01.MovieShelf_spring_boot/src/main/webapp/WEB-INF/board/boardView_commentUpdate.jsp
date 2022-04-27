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
        <c:set var="updateComment" value="${requestScope.updateComment}" scope="page"/>

        <table class="type22">
            <tr>
                <td>작성자</td>
                <td width="200px">댓글</td>
                <td>대댓글</td>
                <td>수정</td>
                <td>삭제</td>
            </tr>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <td><c:out value="${comment.user_id}"/></td>
                    <c:choose>
                        <%-- id 같고, post_no == comment_id 일 때 --%>
                        <c:when test="${ log != null && log.user_id.equals(comment.user_id) && updateComment.comment_id == comment.comment_id }">
                            <form action="/board/comment/updateComment/${comment.comment_id}">
                                <td><input type="text" name="reComment" id="reComment"
                                           value="${comment.comment_content}" required/></td>
                                <td></td>
                                <td><input type="submit" value="+" class="btn btn-primary" /></td>
                                <td></td>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <td><c:out value="${comment.comment_content}"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            <%-- 로그인 되어있으면 => 댓글달기(자기 게시물도 가능)--%>
            <c:if test="${log != null}">
                <form action="/board/comment/addComment/${post.talk_no}">
                    <input type="text" name="comment" id="comment" placeholder="댓글" required/>
                    <input type="submit" value="댓글달기" class="btn btn-primary"/>
                </form>
            </c:if>
        </table>
    </section>
</main>

<script>
    document.getElementById("reComment").focus();
</script>

<c:import url="../footer_.jsp"></c:import>

