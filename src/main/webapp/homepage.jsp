<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>

<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/homepage.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/post.css">
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
            <a href="/profile">Profile Page</a>
        </div>

        <div class="right_nav">
            <a class="button" href="LogOutServlet">Log Out</a>
        </div>

    </div>
</div>

<div class="container">
    <div class="homepage-content">
        <div class="user-post-input">
            <form action="/addPost" method="post">
                <textarea class="user-post-text" name="content" rows="4" cols="50"
                          placeholder="Add a post"></textarea><br>
                <input type="submit" value="Post">
            </form>
        </div>

        <h2>Recent Posts:</h2>
        <c:if test="${not empty posts}">
            <c:forEach var="post" items="${posts}">

                <c:set var="user" value="${userDAO.getUserById(post.userId)}"/>

                <div class="user-post-box">
                    <div class="user-info">
                        <img class="user-image" src="${user.profilePicture}" alt="User Profile Image">
                        <a href="/user/${post.userId}" class="user-link">${user.username}</a>
                    </div>
                    <div class="user-post-content">
                        <div class="user-post-top-content">
                            <c:if test="${post.userId == sessionScope.userId}">
                                <div class="delete-post">
                                    <form action="/DeletePostServlet" method="post">
                                        <input hidden name="postId" value="${post.postId}">
                                        <input hidden name="content" value="${post.content}">
                                        <input type="submit" value="Delete">
                                    </form>
                                </div>
                            </c:if>
                        </div>
                        <div class="user-post-text">
                            <p> ${post.content} </p>
                        </div>

                            <%--Show edit button if the review was written by current user--%>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty posts}">
            <p>No posts available.</p>
        </c:if>

    </div>
</div>

</body>
</html>
