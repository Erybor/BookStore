package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Book;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBook(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO books (title, author, author_id, description, rating, year, cover_url) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getAuthorId());
            statement.setString(4, book.getDescription());
            statement.setDouble(5, book.getRating());
            statement.setInt(6, book.getYear());
            statement.setString(7, book.getCoverUrl());
            statement.executeUpdate();

            addBookGenres(book.getGenres());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBookGenres(List<String> genres) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int bookID = resultSet.getInt(1);

            for (String genre : genres) {
                statement = connection.prepareStatement(
                        "INSERT INTO book_genres (book_id, genre) VALUES (?, ?)");
                statement.setInt(1, bookID);
                statement.setString(2, genre);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void deleteBookGenres(int bookID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book_genres WHERE book_id = ?");
            preparedStatement.setInt(1, bookID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                double rating = resultSet.getDouble("rating");
                int year = resultSet.getInt("year");
                String coverUrl = resultSet.getString("cover_url");
                List<String> genres = getBookGenres(id);

                books.add(new Book(id, title, author, authorId, description, rating, genres, year, coverUrl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private List<String> getBookGenres(int bookID) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT genre FROM book_genres WHERE book_id = ?");
        preparedStatement.setInt(1, bookID);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> genres = new ArrayList<>();

        while (resultSet.next()) {
            genres.add(resultSet.getString(1));
        }

        return genres;
    }

    public void updateBook(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(
//                title=?, author_id=?, description=?, rating=?, year=?, cover_url=?
                "UPDATE books SET title=?, author_id=?, description=?, rating=?, year=?, cover_url=? WHERE book_id=?")) {
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthorId());
            statement.setString(3, book.getDescription());
            statement.setDouble(4, book.getRating());
            statement.setInt(6, book.getYear());
            statement.setInt(7, book.getId());

            statement.executeUpdate();

            deleteBookGenres(book.getId());
            addBookGenres(book.getGenres());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Book> getIntersection(List<Book> list1, List<Book> list2) {
        if (list1.isEmpty()) {
            return list2;
        } // (Line 136 and 150) initially books list is empty

        List<Book> intersection = new ArrayList<>();

        for (Book book : list1) {
            if (list2.contains(book)) {
                intersection.add(book);
            }
        }
        return intersection;
    }

    public List<Book> getBooksByCategory(String category) {
        return getBooksByCategories(List.of(category));
    }

    public List<Book> getBooksByCategories(List<String> categories) {
        List<Book> books = new ArrayList<>();
        try {
            List<List<Book>> booksByCategories = new ArrayList<>();

            for (String category : categories) {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT book_id FROM book_genres WHERE genre = ?");
                statement.setString(1, category);

                ResultSet resultSet = statement.executeQuery();

                List<Book> currentBooks = new ArrayList<>();

                while (resultSet.next()) {
                    currentBooks.add(getBookById(resultSet.getInt(1)));
                }

                booksByCategories.add(currentBooks);
            }

            if (!booksByCategories.isEmpty()) {
                books = booksByCategories.get(0); // Start with books from the first category

                // Find the intersection of books from all categories
                for (int i = 1; i < booksByCategories.size(); i++) {
                    books.retainAll(booksByCategories.get(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

//    public List<Book> getBooksByCategories(List<String> categories) {
//        List<Book> books = new ArrayList<>();
//        try {
//            for (String category : categories) {
//                PreparedStatement statement = connection.prepareStatement(
//                        "SELECT book_id FROM book_genres WHERE genre = ?");
//                statement.setString(1, category);
//
//                ResultSet resultSet = statement.executeQuery();
//
//                List<Book> currentBooks = new ArrayList<>();
//
//                while (resultSet.next()) {
//                    currentBooks.add(getBookById(resultSet.getInt(1)));
//                }
//
//                books = getIntersection(books, currentBooks);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT genre FROM book_genres")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String category = resultSet.getString("genre");
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }


    public void deleteBook(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE book_id=?")) {
            statement.setInt(1, id);

            statement.executeUpdate();

            deleteBookGenres(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book findBook(String title) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM books WHERE title = ?")) {
            statement.setString(1, title);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                int authorId = resultSet.getInt("author_id");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                double rating = resultSet.getDouble("rating");
                int year = resultSet.getInt("year");
                String coverUrl = resultSet.getString("cover_url");
                List<String> genres = getBookGenres(id);
                // Create and return a Book object
                return new Book(id, title, author, authorId, description, rating, genres, year, coverUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book getBookById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM books WHERE book_id = ?")) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int authorId = resultSet.getInt("author_id");
                String description = resultSet.getString("description");
                double rating = resultSet.getDouble("rating");
                int year = resultSet.getInt("year");
                String coverUrl = resultSet.getString("cover_url");
                List<String> genres = getBookGenres(id);

                // Create and return a Book object
                return new Book(id, title, author, authorId, description, rating, genres, year, coverUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO change this to use AuthorId

    public List<Book> getBooksByAuthor(int authorId) {
        List<Book> books = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM books WHERE author_id = ?")) {
            statement.setInt(1, authorId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                double rating = resultSet.getDouble("rating");
                // String genre = resultSet.getString("genre");
                int year = resultSet.getInt("year");
                String coverUrl = resultSet.getString("cover_url");
                List<String> genres = getBookGenres(id);
                Book book = new Book(id, title, author, authorId, description, rating, genres, year, coverUrl);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

}