
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- Header --%>
<jsp:include page="header.jsp"/>

<%-- Content --%>

<section class="mbr-section form1 cid-smQpmI4i2Y" id="form1-k">
    <div class="container">
        <div class="row justify-content-center">
            <div class="title col-12 col-lg-8">
                <h2 class="mbr-section-title align-center pb-3 mbr-fonts-style display-2">
                    Create post
                </h2>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-center">
            <div class="media-container-column col-lg-8" >
                <!---Formbuilder Form--->
                <form action="createPostServlet" method="POST" class="mbr-form form-with-styler">
                    <div class="dragArea row">

                        <div class="col-md-4  form-group" >
                            <label for="name-form1-k" class="form-control-label mbr-fonts-style display-7">Title</label>
                            <input type="text" name="title" required="required" class="form-control display-7" placeholder="Title" id="name-form1-k">
                        </div>


                        <div data-for="message" class="col-md-12 form-group">
                            <label for="message-form1-k" class="form-control-label mbr-fon ts-style display-7">Content</label>
                            <textarea name="content"  class="form-control display-7" placeholder="Content" id="message-form1-k"></textarea>
                        </div>

                        <div>
                            <select name = "vis_status">
                                <option value="all"> All users can see this post </option>
                                <option value="auth_only"> All authorised users can see this post</option>
                                <option value="friends"> Only my friends can see this post </option>
                            </select>
                        </div>
                        <div class="col-md-12 input-group-btn align-center"><button type="submit" class="btn btn-primary btn-form display-4">Create Post<br></button></div>
                    </div>
                </form>
                <!---Formbuilder Form--->
            </div>
        </div>
    </div>
</section>

<%-- Footer --%>
<jsp:include page="footer.jsp"/>




<!--
<form action="createPostServlet" method="POST" class="mbr-form form-with-styler">
    <div class="dragArea row">

        <div class="col-md-4  form-group" data-for="name">
            <label for="name-form1-k" class="form-control-label mbr-fonts-style display-7">Title</label>
            <input type="text" name="title" required="required" class="form-control display-7" placeholder="Title" id="name-form1-k">
        </div>


        <div data-for="message" class="col-md-12 form-group">
            <label for="message-form1-k" class="form-control-label mbr-fon ts-style display-7">Content</label>
            <textarea name="content"  class="form-control display-7" placeholder="Content" id="message-form1-k"></textarea>
        </div>

        <div>
            <select name = "vis_status">
                <option value="all"> All users can see this post </option>
                <option value="auth_only"> All authorised users can see this post</option>
                <option value="friends"> Only my friends can see this post </option>

            </select>
        </div>
        <div class="col-md-12 input-group-btn align-center"><button type="submit" class="btn btn-primary btn-form display-4">Create Post<br></button></div>
    </div>
</form>
-->