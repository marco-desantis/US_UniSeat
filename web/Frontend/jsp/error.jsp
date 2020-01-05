<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 04/01/2020
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Error</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta name="keywords">
    <meta name="description">

    <!-- Favicons -->
    <link href="${pageContext.request.contextPath}/Frontend/img/favicon.png" rel="icon">
    <link href="${pageContext.request.contextPath}/Frontend/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
          rel="stylesheet">

    <!-- Bootstrap CSS File -->
    <link href="${pageContext.request.contextPath}/Frontend/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Libraries CSS Files -->
    <link href="${pageContext.request.contextPath}/Frontend/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Frontend/lib/animate/animate.min.css" rel="stylesheet">

    <!-- Main Stylesheet File -->
    <link href="${pageContext.request.contextPath}/Frontend/css/style.css" rel="stylesheet">
</head>
<body>

<%@include file="header.jsp"%>

<section id="hero">
    <div class="hero-container">
        <h1>Error 404</h1>
    </div>
</section>

<%@include file="footer.jsp"%>

<!-- JavaScript Libraries -->
<script src="${pageContext.request.contextPath}/Frontend/lib/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/Frontend/lib/jquery/jquery-migrate.min.js"></script>
<script src="${pageContext.request.contextPath}/Frontend/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/Frontend/lib/easing/easing.min.js"></script>
<script src="${pageContext.request.contextPath}/Frontend/lib/wow/wow.min.js"></script>
<script src="${pageContext.request.contextPath}/Frontend/lib/waypoints/waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/Frontend/lib/counterup/counterup.min.js"></script>
<script src="${pageContext.request.contextPath}/Frontend/lib/superfish/hoverIntent.js"></script>
<script src="${pageContext.request.contextPath}/Frontend/lib/superfish/superfish.min.js"></script>
<!-- Contact Form JavaScript File -->
<script src="${pageContext.request.contextPath}/Frontend/contactform/contactform.js"></script>
<!-- Template Main Javascript File -->
<script src="${pageContext.request.contextPath}/Frontend/js/main.js"></script>
</body>
</html>