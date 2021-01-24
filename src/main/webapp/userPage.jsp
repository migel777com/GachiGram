<%@ page import="com.example.GachiGram.models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Header --%>
<jsp:include page="header.jsp"/>

<%-- Content --%>
<%-- User Info --%>
<section class="content5 cid-smQnLb9uaS" id="content5-e">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">
                <sql:query var="resultUser" dataSource="jdbc/db">
                    SELECT * from users where user_id = ${sessionScope.userId}
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

<%-- Posts --%>
<section class="content5 cid-smQnLb9uaS" id="content5-e">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">

            </div>
        </div>
    </div>
</section>

<%-- Friends --%>
<section class="content5 cid-smQnLb9uaS" id="content5-e">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">
                Friends list:
                <ul>
                    <%  int id = Integer.parseInt(session.getAttribute("userId").toString());
                        String[] friendlist = User.getFriends(id);
                        for(int i=0; i<friendlist.length; i++){
                            int target_id = User.getUser(friendlist[i]);
                            out.println("<li><a href=\"targetUserPage.jsp?targetId="+target_id+"\">"+friendlist[i]+"</a></li>");
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
</section>

<%-- followers --%>
<section class="content5 cid-smQnLb9uaS" id="content5-e">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">

                Friend Requests list:
                <ul>

                    <%  String[] followerList = User.getFollowers(id);
                        //System.out.println(followerList.length);
                        if (!followerList[0].equals("")) {
                            for (String s : followerList) {
                                int target_id = User.getUser(s);
                                out.println("<li><a href=\"targetUserPage.jsp?targetId="+target_id+"\">" + s + "</a></li>");
                                out.println("<form action=\"AddFriendServlet\" method=\"POST\">");
                                out.println("<input type=\"hidden\" value=\"" + s + "\" name=\"username\">");
                                out.println("<input type=\"submit\" value=\"Accept Request\">");
                                out.println("</form>");
                            }
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
</section>

<%-- Settings --%>




<section class="form8 cid-smQr5xsDlL" id="form8-q">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style align-center mb-0 display-2">
                <strong>Add friend</strong>
            </h3>
        </div>
        <div class="row justify-content-center mt-4">
            <div class="col-lg-8 mx-auto mbr-form">
                <form action="AddFollowerServlet" method="POST" class="mbr-form form-with-styler mx-auto">

                    <div class="dragArea row">
                        <div class="col-lg-4 col-md-12 col-sm-12 form-group">
                            <input type="name" name="username" placeholder="Type username"  class="form-control"  id="email-form8-q">
                        </div>

                        <div class="col-lg-4 col-md-12 col-sm-12 mbr-section-btn align-center">
                            <button type="submit" class="btn btn-primary display-4">Send request</button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<sql:query var="resultSet" dataSource="jdbc/db">
    SELECT * from posts where post_author_id = ${sessionScope.userId}
</sql:query>
<c:forEach items="${resultSet.rows}" var="result">
    <section class="content15 cid-smQoqhA2yf" id="content15-g">
        <div class="container">
            <div class="row justify-content-center">
                <div class="card col-md-12 col-lg-12">
                    <div class="card-wrapper">
                        <div class="card-box align-left">
                            <h4 class="card-title mbr-fonts-style mbr-white mb-3 display-5">
                                <strong>${result.post_title}</strong>
                            </h4>
                            <p class="mbr-text mbr-fonts-style display-7">
                                    ${result.post_content}
                            </p>
                            <p class="card-title mbr-fonts-style mbr-white mb-3 display-5">
                                <strong>Date: ${result.createTime}</strong>
                            </p>
                            <p class="card-title mbr-fonts-style mbr-white mb-3 display-5">
                                <strong>Visibility Status: ${result.post_vis_status}</strong>
                            </p>
                            <p class="card-title mbr-fonts-style mbr-white mb-3 display-5">
                                <strong>Comments Status: ${result.commendable}</strong>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:forEach>




<%-- Footer --%>
<jsp:include page="footer.jsp"/>