<%@ page import="model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Author" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Author Page</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f8e192;
        }

        .author-image {
            width: 200px;
            height: 200px;
            background-color: black; /* Placeholder for author's picture */
        }
        .author-name {
            font-size: 24px;
            font-weight: bold;
        }
        .genres-label {
            font-size: 18px;
            font-weight: bold;
        }
        .book-box {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
            background-color: azure;
        }
        .book-title {
            font-size: 18px;
            font-weight: bold;
        }
        .book-rating {
            font-size: 14px;
        }
    </style>
</head>
<body>
<div>
    <div class="author-image"></div>
    <div class="author-name"><%= ((Author)request.getAttribute("author")).getName() %></div>
    <div class="genres-label">Genres:</div>
    <div class="genres">
        <% Set<String> genres = (Set<String>) request.getAttribute("genres");
            for (String genre : genres) { %>
        <span><%= genre %></span>
        <% } %>
    </div>
    <div class="author-info"><%= ((Author)request.getAttribute("author")).getInfo() %></div>
    <div class="book-list">
        <h3>Books:</h3>
        <% List<Book> bibliography = (List<Book>) request.getAttribute("bibliography");
            for (Book book : bibliography) { %>
        <div class="book-box">
            <div class="book-title"><%= book.getTitle() %></div>
            <div class="book-rating">Rating: <%= book.getRating() %></div>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>