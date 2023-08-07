<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/8/2023
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Book Reviews</title>
  <style>
    body {
      display: flex;
      flex-direction: column;
      align-items: center;
      font-family: Arial, Helvetica, sans-serif;
      background-color: #f8e192;
    }
    .review-box {
      margin-top: 32px;
      width: 80vw;
      display: flex;
      gap: 10px;
      border: solid 1px black;
      padding: 16px;
      background-color: azure;
    }

    .rating-title {
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-weight: bold;
      font-size: 20px;
      width: 100%;
    }

    .review-details {
      display: flex;
      flex-direction: column;
      width: 100%;
      gap: 8px;
    }

    .review-author-year {
      font-style: italic;
    }

    .review-username {
      font-weight: bold;
    }

    .review-image {
      width: 100px;
      height: 130px;
      background-color: black;
    }

    .line {
      width: 100%;
      height: 1px;
      background-color: black;
    }
  </style>
</head>
<body>
<h1>Book Reviews</h1>
<div>
  <%@ page import="model.Book" %>
  <%@ page import="model.Review" %>
  <%@ page import="java.util.Map" %>
  <%@ page import="java.util.List" %>
  <%@ page import="model.User" %>

  <%-- Retrieve the data map from the request attribute --%>
  <% Map<Review, Book> data = (Map<Review, Book>) request.getAttribute("data"); %>
  <% User currUser = (User) request.getAttribute("currUser"); %>

  <%-- Check if the data map is not null and contains the required values --%>
  <% if (data != null ) { %>
  <%-- Retrieve the book and reviews from the data map --%>
  <%--  <% List<Review> reviews = (List<Review>) data.get("reviews"); %>--%>

  <%-- Iterate over the reviews and generate the review boxes --%>
  <% for (Review currReview : data.keySet()) { %>
  <div class="review-box">
    <div class="review-image"></div>
    <div class="review-details">
      <div class="rating-title">
        <div class="review-title"><%= data.get(currReview).getTitle() %></div>
        <div class="review-rating">Rating: <%= currReview.getRating() %></div>
      </div>
      <div class="review-author-year"><%= data.get(currReview).getAuthor() %> | <%= data.get(currReview).getYear() %>
        <a href="/AuthorServlet?authorName=<%= data.get(currReview).getAuthor() %>">    See Author</a>
      </div>
      <div class="line"></div>
      <div class="review-text"><%= currReview.getReviewTxt() %></div>
      <div class="review-username">User: <a href="#"><%= currReview.getUserId() %></a></div>
    </div>
  </div>
  <% } %>
  <% } else { %>
  <p>No reviews found.</p>
  <% } %>
</div>
</body>
</html>