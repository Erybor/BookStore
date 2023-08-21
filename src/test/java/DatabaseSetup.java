import dao.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    private Connection connection;

    public DatabaseSetup(Connection connection) {
        this.connection = connection;

    }

    public void setupDatabase() {


        assert connection != null;
        try (Statement statement = connection.createStatement()) {

            // Drop tables

            // Create schema and tables
            statement.execute("DROP SCHEMA IF EXISTS bookstoretest;");

            statement.execute("CREATE SCHEMA IF NOT EXISTS bookstoretest;");
            statement.execute("USE bookstoretest;");

            statement.execute("DROP TABLE IF EXISTS recommendations, favorites, book_genres, ratings, reviews, books, authors, follows, users, user_posts;");


            statement.execute(
                    "CREATE TABLE users (" +
                            "user_id INT PRIMARY KEY AUTO_INCREMENT," +
                            "username VARCHAR(255) NOT NULL," +
                            "password VARCHAR(255) NOT NULL," +
                            "email VARCHAR(255) NOT NULL," +
                            "name VARCHAR(255)," +
                            "last_name VARCHAR(255)," +
                            "gender VARCHAR(10)," +
                            "profile_picture VARCHAR(255)" +
                            ");"
            );

            statement.execute(
                    "CREATE TABLE authors (" +
                            "author_id INT PRIMARY KEY AUTO_INCREMENT," +
                            "author_name VARCHAR(255) NOT NULL," +
                            "author_info TEXT" +
                            ");"
            );

            statement.execute(
                    "CREATE TABLE books (" +
                            "book_id INT PRIMARY KEY AUTO_INCREMENT," +
                            "title VARCHAR(255) NOT NULL," +
                            "author VARCHAR(255) NOT NULL," +
                            "author_id INT NOT NULL," +
                            "description TEXT," +
                            "rating DECIMAL(3, 2)," +
                            "year INT," +
                            "cover_url VARCHAR(255)" +
                            ");"
            );

            statement.execute(
                    "CREATE TABLE book_genres (" +
                            "book_id INT," +
                            "genre VARCHAR(255)" +
                            ");"
            );

            statement.execute(
                    "CREATE TABLE favorites (" +
                            "favorite_id INT PRIMARY KEY AUTO_INCREMENT," +
                            "user_id INT," +
                            "book_id INT" +
                            ");"
            );

            statement.execute(
                    "CREATE TABLE recommendations (" +
                            "recommendation_id INT PRIMARY KEY AUTO_INCREMENT," +
                            "user_id INT," +
                            "book_id INT" +
                            ");"
            );

            statement.execute(
                    "CREATE TABLE follows (" +
                            "follower_id INT NOT NULL," +
                            "following_id INT NOT NULL," +
                            "PRIMARY KEY (follower_id, following_id)" +
                            ");"
            );

            statement.execute(
                    "CREATE TABLE reviews (" +
                            "review_id INT PRIMARY KEY AUTO_INCREMENT," +
                            "book_id INT," +
                            "user_id INT," +
                            "rating_value INT," +
                            "review_text TEXT" +
                            ");"
            );

            statement.execute(
                    "CREATE TABLE user_posts (" +
                            "post_id INT PRIMARY KEY AUTO_INCREMENT," +
                            "user_id INT," +
                            "content TEXT," +
                            "date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                            ");"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

