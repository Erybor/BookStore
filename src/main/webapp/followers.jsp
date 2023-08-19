<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/navigation_bar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/followers.css">

</head>

<body>
<div class="topnav">
    <div class="test">
        <div class="left_nav">
            <a class="book-verse" style="font-family: Inter" style="font-weight: bold"
               href="welcomePage.jsp">BookVerse</a>
            <a class="home" href="/homepage">Home</a>

        </div>

        <div class="middle_nav">
            <a href="/catalogue">Catalogue</a>
            <a href="/profile">Profile Page</a>
        </div>

        <div class="right_nav">
            <a class="button" href="LogOutServlet">Log Out</a>
        </div>

    </div>
</div>

<div class="main_content">
    <div class="left_div">
        <div class="follower_list">
            <h1>Followers for <a href="/user/${user.id}"> ${user.username} </a></h1>

            <c:forEach var="follow" items="${followers}">

                <c:set var="userDAO" value="${applicationScope['userDAO']}"/>
                <c:set var="followedUser" value="${userDAO.getUserById(follow.followerId)}"/>

                <div class="followed_user">
                    <li><a class="user_link" href="/user/${followedUser.id}">${followedUser.username}</a>
                    </li>
                </div>

            </c:forEach>
        </div>

    </div>
    <div class="right_div">

    </div>
</div>

</div>

</body>
</html>
