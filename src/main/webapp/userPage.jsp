
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

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



<%-- Settings --%>




<section class="form8 cid-smQr5xsDlL" id="form8-q">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style align-center mb-0 display-2">
                <strong>Add friend</strong>
            </h3>
        </div>
        <div class="row justify-content-center mt-4">
            <div class="col-lg-8 mx-auto mbr-form" data-form-type="formoid">
                <form action="https://mobirise.eu/" method="POST" class="mbr-form form-with-styler mx-auto" data-form-title="Form Name"><input type="hidden" name="email" data-form-email="true" value="Je6AO5XeR7ZWBBiCUftBiCeOT4wC5VDBwpw5DJxhu8ypgjLXqIbQ3WpETcARDJGalgAHBniZi4/zvvRfFlAkk+nkFw1tI+vOISu1OZig0qKkcK7OSzWEek/a/XFE9t00">
                    <div class="">
                        <div hidden="hidden" data-form-alert="" class="alert alert-success col-12">Thanks for filling out the form!</div>
                        <div hidden="hidden" data-form-alert-danger="" class="alert alert-danger col-12">Oops...! some problem!</div>
                    </div>
                    <div class="dragArea row">
                        
                        <div class="col-lg-4 col-md-12 col-sm-12 form-group" data-for="email">
                            <input type="email" name="email" placeholder="Login" data-form-field="email" class="form-control" value="" id="email-form8-q">
                        </div>
                        <div class="col-lg-4 col-md-12 col-sm-12 mbr-section-btn align-center">
                            <button type="submit" class="btn btn-primary display-4">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>



<section class="content15 cid-smQoqhA2yf" id="content15-g">
    <div class="container">
        <div class="row justify-content-center">
            <div class="card col-md-12 col-lg-12">
                <div class="card-wrapper">
                    <div class="card-box align-left">
                        <h4 class="card-title mbr-fonts-style mbr-white mb-3 display-5">
                            <strong>Create Site Now</strong>
                        </h4>
                        <p class="mbr-text mbr-fonts-style display-7">
                            Click any text to edit or style it. Select
                            text to insert a link. Click blue "Gear" icon in the top right corner to hide/show buttons,
                            text, title and change the block background. Click red "+" in the bottom right corner to add
                            a new block. Use the top left menu to create new pages, sites and add themes.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="content12 cid-smQnM2cK0m" id="content12-f">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">
                <div class="mbr-section-btn align-center">
                    <a class="btn btn-primary display-4" href="">
                        Sign out<br>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>

<%-- Footer --%>
<jsp:include page="footer.jsp"/>