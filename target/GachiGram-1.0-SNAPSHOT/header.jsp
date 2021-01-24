
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
    <meta name="description" content="Social Network">
    <meta name="generator" content="GachiGram Social Network">
    <link rel="shortcut icon" href="assets/images/icon.png" type="image/x-icon">
    <title>GachiGram</title>
    <link rel="stylesheet" href="assets/tether/tether.min.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="assets/dropdown/css/style.css">
    <link rel="stylesheet" href="assets/socicon/css/styles.css">
    <link rel="stylesheet" href="assets/theme/css/style.css">
    <link rel="stylesheet" href="assets/mobirise/css/mbr-additional.css" type="text/css">
    <link rel="preload" as="style" href="assets/mobirise/css/mbr-additional.css">
</head>
<body>

<section class="menu menu3 cid-smQg6ckY7S" once="menu" id="menu3-1">
    <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-lg">
        <div class="container-fluid">
            <div class="navbar-brand">
                <span class="navbar-logo">
                    <a href="index.jsp">
                        <img src="assets/images/icon.png" alt="GachiGram" style="height: 8rem;">
                    </a>
                </span>
                <span class="navbar-caption-wrap">
                    <a class="navbar-caption text-black display-7" href="index.jsp">
                        GachiGram
                    </a>
                </span>
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <div class="hamburger">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav nav-dropdown nav-right" data-app-modern-menu="true">
                    <c:if test="${sessionScope.userId == null}">
                        <li class="nav-item"><a class="nav-link link text-black display-4" href="index.jsp">Main</a></li>
                        <li class="nav-item"><a class="nav-link link text-black display-4" href="login.jsp">Log in</a></li>
                        <li class="nav-item"><a class="nav-link link text-black display-4" href="register.jsp">Register</a></li>
                    </c:if>

                    <c:if test="${sessionScope.userId != null}">
                        <li class="nav-item"><a class="nav-link link text-black display-4" href="index.jsp">Main</a></li>
                        <li class="nav-item"><a class="nav-link link text-black display-4" href="userPage.jsp">UserPage</a></li>
                        <li class="nav-item"><a class="nav-link link text-black display-4" href="LogOutServlet">Log out</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</section>