<%@ page import="com.example.GachiGram.models.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%-- Header --%>
<jsp:include page="header.jsp"/>

<%
    String str1;
    if (session.getAttribute("username") == null) {
        str1 = "";
    } else {
        str1 = session.getAttribute("username").toString();
    }
    int str2 = Integer.parseInt(request.getParameter("targetId"));
    String temp = User.isFriends(str1, str2);
    out.println(temp);
    System.out.println(temp);
    session.setAttribute("logic_cond", temp);
%>

<%-- Content --%>
<%-- User Info --%>
<section class="content5 cid-smQnLb9uaS" id="content5-e">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">
                <sql:query var="resultUser" dataSource="jdbc/db">
                    SELECT * from users where user_id = <%=request.getParameter("targetId")%>
                </sql:query>
                <c:forEach items="${resultUser.rows}" var="userRow">
                    <h4 class="mbr-section-subtitle mbr-fonts-style mb-4 display-5">
                        User: ${userRow.username}
                    </h4>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<sql:query var="resultSet" dataSource="jdbc/db">
    SELECT * from posts where post_author_id = <%=request.getParameter("targetId")%>
</sql:query>

<section class="content15 cid-smQoqhA2yf" id="content15-g">
    <div class="container">
        <div class="row justify-content-center">
            <div class="card col-md-12 col-lg-12">
                <%-- Posts --%>
                <c:choose>
                    <%-- No Posts --%>
                    <c:when test="${resultSet.rowCount == 0}">
                        <div class="card-wrapper">
                            <div class="card-box align-left">
                                <h4 class="card-title mbr-fonts-style mbr-white mb-3 display-5">
                                    <strong>- THERE IS NO POSTS TO DISPLAY -</strong>
                                </h4>
                            </div>
                        </div>
                    </c:when>
                    <%-- There are Posts --%>
                    <c:otherwise>
                        <c:forEach items="${resultSet.rows}" var="result">
                            <%-- Vis_Status of Posts --%>
                            <c:choose>
                                <%-- Auth --%>
                                <c:when test="${result.post_vis_status == 'auth_only' && sessionScope.userId == null}"> </c:when>
                                <%-- Friends --%>
                                <c:when test="${result.post_vis_status == 'friends' && sessionScore.logic_cond == 'no' }"> </c:when>
                                <%-- All --%>
                                <c:otherwise>
                                    <div class="card-wrapper">
                                        <div class="card-box align-left">
                                            <h4 class="card-title mbr-fonts-style mbr-white mb-3 display-5">
                                                <strong>${result.post_title}</strong>
                                            </h4>
                                            <p class="mbr-text mbr-fonts-style display-7">
                                                    ${result.post_content}
                                            </p>
                                            <p class="card-title mbr-fonts-style mbr-white mb-3 display-7">
                                                <strong>Date: ${result.createTime}</strong>
                                            </p>
                                            <p class="card-title mbr-fonts-style mbr-white mb-3 display-7">
                                                <strong>Visibility Status: ${result.post_vis_status}</strong>
                                            </p>
                                            <p class="card-title mbr-fonts-style mbr-white mb-3 display-7">
                                                <strong>Comments Status: ${result.commendable}</strong>
                                            </p>
                                        </div>
                                    </div>

                                    <%-- Comments Block --%>
                                    <sql:query var="commentsSet" dataSource="jdbc/db">
                                        SELECT * from comments where comment_post_id = ${result.post_id}
                                    </sql:query>
                                    <c:choose>
                                        <c:when test="${commentsSet.rowCount == 0}">
                                            <div class="card-wrapper">
                                                <div class="card-box align-left">
                                                    <h4 class="card-title mbr-fonts-style mbr-white mb-3 display-8">
                                                        <strong>- THERE IS NO COMMENTS TO DISPLAY -</strong>
                                                    </h4>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${commentsSet.rows}" var="resultForComments">
                                                <div class="card-wrapper">
                                                    <div class="card-box align-right">
                                                        <h4 class="card-title mbr-fonts-style mbr-white mb-3 display-8">
                                                            <strong>Comment<br>Author: ${resultForComments.comment_author_id}</strong>
                                                        </h4>
                                                        <p class="mbr-text mbr-fonts-style display-8">
                                                                ${resultForComments.comment_content}
                                                        </p>
                                                        <p class="card-title mbr-fonts-style mbr-white mb-3 display-8">
                                                            <strong>Date: ${resultForComments.createTime}</strong>
                                                        </p>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>

                                    <%-- Leave Comment --%>
                                    <c:choose>
                                        <c:when test="${result.commendable == true}">
                                            <form action="LeaveCommentServlet" method="post" class="mbr-form form-with-styler mx-auto">
                                                <div class="dragArea row">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 form-group">
                                                        <input type="hidden" name="target_id" class="form-control" id="target-id-form6-5" value="<%=request.getParameter("targetId")%>">
                                                        <input type="hidden" name="comment_post_id" class="form-control" id="post-id-form6-5" value="${result.post_id}">
                                                        <input type="text" name="comment_content" placeholder="Content" class="form-control" id="content-form6-5" required>
                                                    </div>
                                                    <div class="col-auto mbr-section-btn align-center"><button type="submit" name="submit" class="btn btn-primary display-4">Leave Comment<br></button></div>
                                                </div>
                                            </form>
                                        </c:when>
                                    </c:choose>
                                    <%-- Leave Comment END --%>
                                    <%-- Comments Block END --%>
                                    <br><br><br>
                                </c:otherwise>
                            </c:choose>
                            <%-- Vis_Status of Posts END --%>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <%-- Posts END --%>
            </div>
        </div>
    </div>
</section>



<%-- Footer --%>
<jsp:include page="footer.jsp"/>