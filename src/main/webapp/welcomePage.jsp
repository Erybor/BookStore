<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>

<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/welcomePage.css">
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
               href="/welcome">BookVerse</a>
            <a class="home" href="/welcome">Home</a>
        </div>

        <%--        <div class="middle_nav">--%>
        <%--            <a href="/catalogue">Catalogue</a>--%>
        <%--            <a href="#profile_page">Profile Page</a>--%>
        <%--        </div>--%>

        <div class="right_nav">
            <a class="button" href="login.jsp">Log in</a>
            <a href="register.jsp">Register</a>
        </div>

    </div>
</div>


<div class="container">
    <div class="left-div">
        <div style="width: 613px; height: 271px; color: black; font-weight: bold; font-size: 60px; font-family: Outfit; text-transform: capitalize; line-height: 60px; word-wrap: break-word">
            Welcome to our Book Lovers' Community – Your Gateway to a Literary Journey
        </div>

        <p style="font-weight: bold">A Place to Explore, Discuss, and Celebrate the World of Books.</p>

        <a class="join_click" href="register.jsp">JOIN NOW</a>

    </div>
    <div class="right-div">
        <img style="width: 345px; height: 311px; opacity: 1;" src="logo.png"/>
    </div>

</div>

</body>
</html>
