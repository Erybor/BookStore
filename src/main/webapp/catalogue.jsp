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
    <link rel="stylesheet" type="text/css" href="catalogue.css">
</head>

<body>


<%--<%--%>
<%--    BookDAO bookDAO = (BookDAO) request.getServletContext().getAttribute("bookDAO");--%>
<%--    List<String> categories = bookDAO.getAllCategories();--%>
<%--    HashMap<String, List<Book>> booksByCategories = new HashMap<>();--%>

<%--    // Iterate through the categories and retrieve corresponding books for each category--%>
<%--    for (String category : categories) {--%>
<%--        List<Book> books = bookDAO.getBooksByCategory(category);--%>
<%--        booksByCategories.put(category, books);--%>
<%--    }--%>

<%--    // Set the categories as an attribute in the request--%>
<%--    request.setAttribute("categories", booksByCategories);--%>
<%--%>--%>

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
        <h1 class="header">Book Catalogue</h1>

        <div class="genres">
            <h2 class="header">Genres</h2>

            <c:forEach items="${categories}" var="category">
                <div class="genre_div">
                    <a href="/genre/${category.key}"><c:out value="${category.key}"/></a>
                    <div class="cover_row">
                        <c:forEach items="${category.value}" var="book">
                            <div class="book_cover">
                                <a href="/book/${book.id}">
                                    <img src="book.png" alt="${book.title}" width="115" height="180"/>
                                        <%--                                                                        <p>${book.title}</p>--%>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
    <div class="right_div">
        <h2>Filter by Genre</h2>
        <form action="SearchGenreServlet" method="get" onsubmit="return submitForm()">
            <div id="genreOptions">
                <c:forEach var="genre" items="${genreListInput}">
                    <span class="genreOption" onclick="toggleGenre(this)"><c:out value="${genre}"/></span>
                </c:forEach>
            </div>
            <input type="hidden" id="selectedGenres" name="genre">

            <div id="genresList"></div>

            <div class="buttons">
                <button class="clear_button" type="button" onclick="clearGenres()">Clear</button>
                <button class="search_button" type="submit">Search Books</button>
            </div>
        </form>

        <script>
            var genres = [];
            var selectedGenresInput = document.getElementById("selectedGenres");
            console.log(selectedGenresInput);

            var genreOptions = document.getElementsByClassName("genreOption");
            for (var i = 0; i < genreOptions.length; i++) {
                genreOptions[i].addEventListener("click", toggleGenre);
            }

            function toggleGenre() {
                this.classList.toggle("selectedGenre");
                var genre = this.textContent;
                if (genres.includes(genre)) {
                    genres = genres.filter(function (value) {
                        return value !== genre;
                    });
                } else {
                    genres.push(genre);
                }
                // displayGenres();
                updateSelectedGenresInput();
            }

            function updateSelectedGenresInput() {
                selectedGenresInput.value = genres.join(",");
            }

            function clearGenres() {
                genres = [];
                updateSelectedGenresInput();
                var selectedGenreOptions = document.getElementsByClassName("selectedGenre");
                while (selectedGenreOptions.length > 0) {
                    selectedGenreOptions[0].classList.remove("selectedGenre");
                }
            }
        </script>
    </div>
</div>

</body>
</html>
