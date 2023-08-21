<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>


<html>
<head>
    <title>Book Catalogue</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/author.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/navigation_bar.css">

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
            <a href="/profile">Profile Page</a>
        </div>

        <div class="right_nav">
            <a class="button" href="LogOutServlet">Log Out</a>
        </div>

    </div>
</div>

<%--<c:set var="userDAO" value="${applicationScope['userDAO']}"/>--%>
<%--<c:set var="userId" value="${sessionScope.userId}"/>--%>

<%--<c:set var="user" value="${userDAO.getUserByID(userId)}"/>--%>

<div class="main_content">
    <div class="left_div">
        <div class="book_cover">
            <img src="/${book.coverUrl}" width="208" height="312"/>
        </div>
    </div>

    <div class="right_div">
        <div class="review_edit">
            <h1>Edit Review for Book:</h1>
            <h2>${book.title}</h2>

            <form action="/EditReviewServlet" method="post">
                <input type="hidden" name="reviewId" value="${reviewId}">

                <div class="review_rating">
                    <label for="rating">Rating:</label>
                    <input type="number" id="rating" name="rating" min="1" max="5" value="${review.ratingValue}"
                           required>
                </div>

                <div class="review_text" style="padding: 2rem 0rem; display: flex; flex-direction: column">
                    <label for="reviewText" style="margin-bottom: 1rem">Review Text:</label>
                    <textarea id="reviewText" name="reviewText" rows="4" cols="50"
                              required>${review.reviewText}</textarea>
                </div>


                <input type="submit" value="Save Changes">
            </form>
        </div>
    </div>
</div>

</div>

</body>
</html>
