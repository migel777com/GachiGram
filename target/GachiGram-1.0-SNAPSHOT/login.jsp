<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- Header --%>
<jsp:include page="header.jsp"/>

<%-- Content --%>
<section class="form6 cid-smQhUAsNC4" id="form6-5">
    <div class="container-fluid">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style align-center mb-0 display-2">
                <strong>Log in</strong><strong><br></strong>
            </h3>
        </div>
        <div class="row justify-content-center mt-4">
            <div class="col-lg-8 mx-auto mbr-form">
                <span><% if (request.getAttribute("logMessage") != null) request.getAttribute("logMessage"); %></span>
                <form action="LogServlet" method="post" class="mbr-form form-with-styler mx-auto">
                    <div class="dragArea row">
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" >
                            <input type="text" name="username" placeholder="Login" class="form-control" id="name-form6-5" required>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group">
                            <input type="password" name="password" placeholder="Password" class="form-control" id="email-form6-5" required>
                        </div>
                        <div class="col-auto mbr-section-btn align-center"><button type="submit" name="submit" class="btn btn-primary display-4">Log in<br></button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<%-- Footer --%>
<jsp:include page="footer.jsp"/>