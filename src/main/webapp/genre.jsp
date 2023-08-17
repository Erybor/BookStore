<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>
<head>
    <title>Book Catalogue</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/genre.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/navigation_bar.css">


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
            <a href="#profile_page">Profile Page</a>
        </div>

        <div class="right_nav">
            <a class="button" href="LogOutServlet">Log Out</a>
        </div>

    </div>
</div>

<div class="main_content">

    <div class="left_div">
        <h1 class="header">Catalogue</h1>

        <div class="genres">
            <%--            if the list is empty, then we have to display a message saying that no books were found--%>
            <c:if test="${empty filteredBooks}">
                <p>No books found.</p>

                <a href=""> Go Back </a>
            </c:if>
        </div>

        <%--            If the list is not empty, then we have to display the books--%>
        <c:if test="${!empty filteredBooks}">
            <%--                <h2 class="header">List of books with genres: ${genres}</h2>--%>
            <h2 class="header">List of books with genres:</h2>
            <ul class="genre-list">
                <c:forEach var="genre" items="${genres}">
                    <li><a href="/genre/${genre}">${genre}</a></li>
                </c:forEach>
            </ul>


            <div class="cover_row">
                <c:forEach items="${filteredBooks}" var="book">
                    <div class="book_cover">
                        <p></p>
                        <a href="${pageContext.request.contextPath}/book/${book.id}">
                            <img src="/${book.coverUrl}" alt="${book.title}" width="115" height="180"/>
                        </a>
                    </div>
                </c:forEach>
            </div>

        </c:if>


    </div>
    <div class="right_div">


    </div>
</div>

</body>
</html>
