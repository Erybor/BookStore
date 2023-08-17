<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%@ page import="dao.BookDAO" %>

<html>
<head>
    <title>Book Catalogue</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/author.css">

    <%--    FONTAWESOME STYLESHEET FOR STAR--%>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
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
        <div class="author_cover">
            <%--            <img src="${pageContext.request.contextPath}/book.png" width="208" height="312"/>--%>
            <img src="/tolkien.jpg" width="208" height="312"/>

        </div>
    </div>
    <div class="right_div">
        <div class="author_title">
            <h1> ${author.name} </h1>
        </div>

        <div class="author_info">
            <h3> About the author: </h3>
            <c:if test="${empty author.info}">
                <p> No information available </p>
            </c:if>
            <c:if test="${!empty author.info}">
                <p> ${author.info}</p>

            </c:if>
        </div>

        <div class="book_author_name">
            <a class="author_link" href="/author/${book.authorId}">
                <h2> ${book.author} </h2>
            </a>
        </div>

        <div class="author_books">
            <h3> Books by this author: </h3>
            <c:if test="${empty authorBooks}">
                <p> No books available </p>
            </c:if>

            <ul class="author_books_list">
                <c:if test="${!empty authorBooks}">
                    <c:forEach var="book" items="${authorBooks}">
                        <li><a class="book_link" href="/book/${book.id}">
                            <p> ${book.title} </p>
                        </a></li>
                    </c:forEach>
                </c:if>
            </ul>

        </div>


    </div>
</div>

</div>

</body>
</html>
