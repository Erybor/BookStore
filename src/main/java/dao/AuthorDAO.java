package dao;

import model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAuthor(Author author) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO authors (author_name, author_info) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, author.getName());
            statement.setString(2, author.getInfo());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int generatedAuthorId = rs.getInt(1);
                author.setId(generatedAuthorId);
            }

        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void updateAuthor(Author author) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE authors SET author_name=?, author_info=? WHERE author_id=?");
            statement.setString(1, author.getName());
            statement.setString(2, author.getInfo());
            statement.setInt(3, author.getId());
            statement.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteAuthor(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM authors WHERE author_id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("author_id");
                String name = resultSet.getString("author_name");
                String info = resultSet.getString("author_info");
                authors.add(new Author(id, name, info));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return authors;
    }

    public Author getAuthor(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors WHERE author_id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("author_name");
                String info = resultSet.getString("author_info");
                return new Author(id, name, info);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }


    public Author getAuthor(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors WHERE author_name=?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("author_id");
                String authorName = resultSet.getString("author_name");
                String info = resultSet.getString("author_info");
                return new Author(id, authorName, info);
            }
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }
}
