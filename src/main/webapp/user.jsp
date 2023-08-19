<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>


<html>
<head>
    <title>Book Catalogue</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/profile.css">
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
            <a class="button" href="LogOutServlet">Log Out</a>
        </div>

    </div>
</div>

<%--<c:set var="userDAO" value="${applicationScope['userDAO']}"/>--%>
<%--<c:set var="userId" value="${sessionScope.userId}"/>--%>

<%--<c:set var="user" value="${userDAO.getUserByID(userId)}"/>--%>

<div class="main_content">
    <div class="left_div">
        <div class="user_cover">
            <img src="${user.profilePicture}" width="208" height="312"/>

        </div>

        <c:if test="${following}">
            <div class="follow_button">
                <form action="/RemoveFollowerServlet" method="post">
                    <input type="hidden" name="followerId" value="${sessionScope.userId}"/>
                    <input type="hidden" name="followingId" value="${user.id}"/>
                    <button type="submit">Unfollow</button>
                </form>
            </div>

        </c:if>

        <c:if test="${!following}">
            <div class="follow_button">
                <form action="/AddFollowerServlet" method="post">
                    <input type="hidden" name="followerId" value="${sessionScope.userId}"/>
                    <input type="hidden" name="followingId" value="${user.id}"/>
                    <button type="submit">Follow</button>
                </form>
            </div>
        </c:if>

        <div class="follows">
            <p> Followers: <a href="/followers/${user.id}"> ${followerCount}</a>
            </p>
        </div>


    </div>
    <div class="right_div">
        <div class="user_name">
            <h1> pepe ${user.username} </h1>
        </div>
        <div class="user_info">

            <div style="display: flex; flex-direction: column; gap: 10px;">
                <span> Name: ${user.name}</span>
                <span> Last Name: ${user.lastName}</span>
                <span> Gender: ${user.gender}</span>
            </div>

            <div class="user_reviews">
                <h3> User Reviews: </h3>
                <c:if test="${empty reviews}">
                    <p> The User has no Reviews </p>
                </c:if>

                <ul class="user_reviews_list">
                    <c:if test="${!empty reviews}">
                        <c:forEach var="review" items="${reviews}">

                            <c:set var="bookDAO" value="${applicationScope['bookDAO']}"/>
                            <c:set var="book" value="${bookDAO.getBookById(review.bookId)}"/>


                            <div>
                                <li><a class="book_link" href="/book/${book.id}">${book.title} </a>
                                    <span> --- </span>
                                        <%--                                    <a class="review_link" href="/review/${review.reviewId}?bookId=${book.id}"> </a>--%>
                                    <span> Rating: ${review.ratingValue}</span>
                                        <%--                                    <div> Review: ${review.reviewText}</div>--%>
                                    <div class="review-box">
                                        <div class="review-content">
                                                <%--                                            <p class="review-rating">Rating: ${review.ratingValue}</p>--%>
                                            <p class="review-text">${review.reviewText}</p>
                                        </div>
                                    </div>
                                </li>
                            </div>

                        </c:forEach>
                    </c:if>
                </ul>

            </div>

        </div>
    </div>

</div>

</body>
</html>
