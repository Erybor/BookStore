<%@ page import="model.BookList" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Book List</title>
  <style>
    /*body {*/
    /*  text-align: center;*/
    /*  font-family: Arial, sans-serif;*/
    /*}*/
    body {
      display: flex;
      flex-direction: column;
      align-items: center;
      font-family: Arial, Helvetica, sans-serif;
      background-color: #f8e192;
    }

    h1 {
      margin-top: 40px;
    }

    /*.book {*/
    /*  margin: 20px auto;*/
    /*  width: 400px;*/
    /*  border: 1px solid #ccc;*/
    /*  padding: 10px;*/
    /*  background-color: #f9f9f9;*/
    /*}*/

    .book {
      margin-top: 32px;
      width: 80vw;
      /*display: flex;*/
      gap: 10px;
      border: solid 1px black;
      padding: 16px;
      background-color: azure;
    }
    .add-form {
      margin-top: 20px;
    }

    .add-input {
      width: 300px;
      padding: 5px;
      margin-right: 10px;
    }

    .add-button {
      padding: 5px 10px;
      background-color: #0066ff;
      color: #fff;
      border: none;
      cursor: pointer;
    }
  </style>
</head>
<body>
<% boolean myPage = (Boolean)request.getAttribute("myPage"); %>
<%
  BookList bookList = (BookList) request.getAttribute("bookList");
%>
<% User user = (User)request.getAttribute("user"); %>


<h1>List: <%= bookList.getTitle() %> by <%= user.getUsername() %></h1>
<% if(myPage) { %>
<div class="add-form">
  <form action="AddBookToListServlet" method="post">
    <input type="text" name="bookTitle" class="add-input" placeholder="Enter Book Name" required>
    <input type="hidden" name="listID" value="<%= bookList.getListId() %>">
    <input type="hidden" name="userID" value="<%= bookList.getUserId() %>">
    <input type="hidden" name="sourceJSP" value="displayList">
    <button type="submit" class="add-button">Add to List</button>
  </form>
  <%-- display error if something went wrong while adding  --%>
  <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
  <% if (errorMessage != null) { %>
  <p class="error-message"><%= errorMessage %></p>
  <% } %>
</div>
<% } %>
<div>
  <% for (model.Book book : bookList.getBooks()) { %>
  <div class="book">
    <h2><%= book.getTitle() %></h2>
    <p>Author: <%= book.getAuthor() %></p>
    <p>Description: <%= book.getDescription() %></p>
    <p>Rating: <%= book.getRating() %></p>
    <p>Genres: <% List<String> genres = book.getGenres(); for(String genre : genres) { %>
            <%= genre+ "  "%>
      <% } %>
    </p>
<%--   <p>Genres:   <%= book.getGenres().toString() %></p>--%>
    <p>Year: <%= book.getYear() %></p>
    <% if(myPage) { %>
    <form action="DeleteBookFromListServlet" method="POST" style="position: relative;">
      <input type="hidden" name="bookID" value="<%= book.getId() %>">
      <input type="hidden" name="listID" value="<%= bookList.getListId() %>">
      <%--      <input type="hidden" name="userID" value="<%= bookList.getUserId() %>">--%>
      <input type="hidden" name="sourceJSP" value="displayList">
      <button type="submit" style="position: absolute; bottom: 0; right: 0;">Delete Book</button>
    </form>
    <% } %>
  </div>
  <% } %>
</div>
</body>
</html>