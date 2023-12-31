<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%@ page import="dao.BookDAO" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="model.User" %>


<html>
<head>
    <title>Book Catalogue</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/BookPage.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/navigation_bar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/review.css">


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

            <a class="button" href="/LogOutServlet">Log Out</a>
        </div>

    </div>
</div>

<div class="main_content">

    <div class="left_div">
        <div class="book_cover">
            <img src="${pageContext.request.contextPath}/${book.coverUrl}" width="208" height="312"/>
        </div>
    </div>
    <div class="right_div">
        <div class="book_title">
            <h1> ${book.title} </h1>
        </div>

        <div class="book_rating">
            <p> Rating: ${book.rating} </p>
            <%--            <div class="stars">--%>
            <%--                <i class="fa-star"></i>--%>
            <%--                <i class=" fa-star"></i>--%>
            <%--                <i class="fa-star"></i>--%>
            <%--                <i class="fa-star"></i>--%>
            <%--                <i class="fa-star"></i>--%>
            <%--            </div>--%>

        </div>

        <div class="book_author_name">
            <a class="author_link" href="/author/${book.authorId}">
                <h2 class="author-name"> ${book.author} </h2>
            </a>
        </div>

        <div class="book_description">
            <p> ${book.description} </p>
        </div>


        <div class="book-genres-list">
            <span>Genres: </span>

            <c:forEach var="genre" items="${book.genres}">
                <a href="/genre/${genre}">${genre},</a>
            </c:forEach>

        </div>



        <div class="add_review">
            <h3>Write a Review</h3>
            <form action="/AddReviewServlet" method="post">
                <input type="hidden" name="bookId" value="${bookId}">
                <textarea name="review" rows="4" cols="50" placeholder="Write your review"></textarea>
                <br>
                <label for="rating">Rating:</label>

                <input type="number" id="rating" name="rating" min="1" max="5">
                <br>
                <div class="submit_review_button">
                    <input type="submit" value="Submit Review">
                </div>
            </form>

            <%--                <p> ${reviewError}</p>--%>
            <c:if test="${reviewError}">
                <div class="review_error">
                        <%--Set review error to false--%>
                    <c:set var="reviewError" value="${false}" scope="session"/>

                    <p> You have already reviewed this book, please edit your review instead! </p>
                    <form action="/review/${reviewId}" method="get">
                        <input type="submit" value="Edit Review">
                    </form>

                </div>
            </c:if>

        </div>


        <%--        // Save user in a JSTL variable--%>

        <c:set var="userDAO" value="${applicationScope.userDAO}"/>
        <%--        <c:set var="user" value="${applicationScope.userDAO}"/>--%>
        <div class="book_reviews">
            <h3>Reviews</h3>
            <c:forEach var="review" items="${reviews}">
                <c:set var="user" value="${userDAO.getUserById(review.userId)}"/>


                <div class="review-box">
                    <div class="user-info">
                        <img class="user-image" src="${user.profilePicture}" alt="User Profile Image">
                        <a href="/user/${review.userId}" class="user-link">${user.username}</a>
                    </div>
                    <div class="review-content">
                        <div class="review-top-content">
                            <p class="review-rating">Rating: ${review.ratingValue}</p>
                            <c:if test="${review.userId == sessionScope.userId}">
                                <div class="edit-review">
                                    <button class="edit-review-button">
                                        <a href="/review/${review.reviewId}">Edit</a>
                                    </button>
                                </div>
                            </c:if>
                        </div>
                        <p class="review-text">${review.reviewText}</p>

                            <%--Show edit button if the review was written by current user--%>
                    </div>
                </div>
            </c:forEach>
        </div>

    </div>
</div>

</div>

<script>
    var genre = document.getElementsByClassName("book-genres-list")[0];
    var a_tags = genre.getElementsByTagName("a");
    var last_a_tag = a_tags[a_tags.length - 1];
    last_a_tag.innerHTML = last_a_tag.innerHTML.slice(0, -1);
</script>

</body>
</html>
