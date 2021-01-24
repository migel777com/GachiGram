<%@ page import="com.example.GachiGram.models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Header --%>
<jsp:include page="header.jsp"/>

<%-- Content --%>
<section class="header4 cid-smQpF7aAlC mbr-fullscreen mbr-parallax-background" id="header4-n">
    <div class="container">
        <div class="row">
            <div class="content-wrap">
                <h1 class="mbr-section-title mbr-fonts-style mbr-white mb-3 display-1">
                    <strong>GachiGram</strong>
                </h1>
                <p class="mbr-fonts-style mbr-text mbr-white mb-3 display-7">
                    The social network of the future
                </p>
            </div>
        </div>
    </div>
</section>

<section class="content5 cid-smQnLb9uaS" id="content5-e">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">
                People around the world:
                <ul>
                    <sql:query var="resultUser" dataSource="jdbc/db">
                        SELECT * from users
                    </sql:query>
                    <c:forEach items="${resultUser.rows}" var="userRow">
                        <li><a href="targetUserPage.jsp?targetId=${userRow.user_id}">${userRow.username}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</section>


<%-- Footer --%>
<jsp:include page="footer.jsp"/>