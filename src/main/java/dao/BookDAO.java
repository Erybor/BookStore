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
            String sql = "INSERT INTO books (title, author, author_id, description, rating, year, cover_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
            // Specify that we want to retrieve generated keys
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getAuthorId());
            statement.setString(4, book.getDescription());
            statement.setDouble(5, book.getRating());
            statement.setInt(6, book.getYear());
            statement.setString(7, book.getCoverUrl());
            statement.executeUpdate();

            addBookGenres(book.getGenres());

            // Set the generated ID to the book object
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int generatedBookId = rs.getInt(1);
                book.setId(generatedBookId);
            }

        } catch (SQLException e) { e.printStackTrace();}
    }

    private void addBookGenres(List<String> genres) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int bookID = resultSet.getInt(1);

            for (String genre : genres) {
                statement = connection.prepareStatement("INSERT INTO book_genres (book_id, genre) VALUES (?, ?)");
                statement.setInt(1, bookID);
                statement.setString(2, genre);

                statement.executeUpdate();
            }
        } catch (SQLException e) { e.printStackTrace();}

    }

    private void deleteBookGenres(int bookID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book_genres WHERE book_id = ?");
            preparedStatement.setInt(1, bookID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private List<String> getBookGenres(int bookID) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT genre FROM book_genres WHERE book_id = ?");
        preparedStatement.setInt(1, bookID);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> genres = new ArrayList<>();

        while (resultSet.next()) {
            genres.add(resultSet.getString(1));
        }

        return genres;
    }

    public List<Book> getBooksByCategory(String category) {
        return getBooksByCategories(List.of(category));
    }

    public List<Book> getBooksByCategories(List<String> categories) {
        List<Book> books = new ArrayList<>();
        try {
            List<List<Book>> booksByCategories = new ArrayList<>();

            for (String category : categories) {
                PreparedStatement statement = connection.prepareStatement("SELECT book_id FROM book_genres WHERE genre = ?");
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
        } catch (SQLException e) { e.printStackTrace(); }
        return books;
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT genre FROM book_genres")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String category = resultSet.getString("genre");
                categories.add(category);
            }
        } catch (SQLException e) { e.printStackTrace();}
        return categories;
    }


    public void deleteBook(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE book_id=?")) {
            statement.setInt(1, id);

            statement.executeUpdate();

            deleteBookGenres(id);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Book getBookById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE book_id = ?")) {
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
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Book> getBooksByAuthor(int authorId) {
        List<Book> books = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE author_id = ?")) {
            statement.setInt(1, authorId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                double rating = resultSet.getDouble("rating");
                int year = resultSet.getInt("year");
                String coverUrl = resultSet.getString("cover_url");
                List<String> genres = getBookGenres(id);
                Book book = new Book(id, title, author, authorId, description, rating, genres, year, coverUrl);
                books.add(book);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return books;
    }

    public void updateBookRating(int bookId, double rating) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE books SET rating=? WHERE book_id=?")) {
            statement.setDouble(1, rating);
            statement.setInt(2, bookId);
            statement.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }
    }

}