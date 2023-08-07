<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>

<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/homepage.css">
    <link href='https://fonts.googleapis.com/css?family=Outfit&display=swap' rel='stylesheet'>
    <link rel="preconnect" href="https://rsms.me/">
    <link rel="stylesheet" href="https://rsms.me/inter/inter.css">
    <title>${pageContext.request.contextPath}</title>
</head>

<body>

<div class="topnav">
    <div class="test">
        <div class="left_nav">
            <a class="book-verse" style="font-family: Inter" style="font-weight: bold"
               href="/homepage">BookVerse</a>
            <a class="home" href="/homepage">Home</a>
        </div>

        <div class="middle_nav">
            <a href="/catalogue">Catalogue</a>
            <a href="#profile_page">Profile Page</a>
        </div>

        <div class="right_nav">
            <a class="button" href="LogOutServlet">Log Out</a>
        </div>

    </div>
</div>

<div class="container">
    <div class="left-div">


    </div>
    <div class="right-div">
    </div>

</div>

</body>
</html>
