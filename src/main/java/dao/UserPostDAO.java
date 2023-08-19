package dao;

import model.UserPost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserPostDAO {
    private Connection connection;

    public UserPostDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert a new post
    public void addUserPost(UserPost post) {

        String query = "INSERT INTO user_posts (user_id, content) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, post.getUserId());
            statement.setString(2, post.getContent());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Retrieve a post by its ID
    public UserPost getPostById(int postId) {
        String query = "SELECT * FROM user_posts WHERE post_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, postId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    String content = resultSet.getString("content");
                    Timestamp dateCreated = resultSet.getTimestamp("date_created");
                    return new UserPost(postId, userId, content, dateCreated);
                }
                return null; // No post found with the given ID
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Retrieve all posts for a specific user
    public List<UserPost> getPostByUserId(int userId) {
        List<UserPost> posts = new ArrayList<>();
        String query = "SELECT * FROM user_posts WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int postId = resultSet.getInt("post_id");
                    String content = resultSet.getString("content");
                    Timestamp dateCreated = resultSet.getTimestamp("date_created");
                    posts.add(new UserPost(postId, userId, content, dateCreated));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    public List<UserPost> getAllPosts() {
        List<UserPost> posts = new ArrayList<>();
        String query = "SELECT * FROM user_posts";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int postId = resultSet.getInt("post_id");
                    int userId = resultSet.getInt("user_id");
                    String content = resultSet.getString("content");
                    Timestamp dateCreated = resultSet.getTimestamp("date_created");
                    posts.add(new UserPost(postId, userId, content, dateCreated));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    // Returns all posts for users that the given user follows
    public List<UserPost> getFollowerPosts(int userId) {
        List<UserPost> posts = new ArrayList<>();
        String query = "SELECT * FROM user_posts WHERE user_id IN (SELECT following_id FROM follows WHERE follower_id = ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int postId = resultSet.getInt("post_id");
                    int postUserId = resultSet.getInt("user_id");
                    String content = resultSet.getString("content");
                    Timestamp dateCreated = resultSet.getTimestamp("date_created");
                    posts.add(new UserPost(postId, postUserId, content, dateCreated));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;

    }

    public void updatePost(int reviewId, String newReviewText, Timestamp newDate) {
        String query = "UPDATE user_posts SET content = ?, date_created = ? WHERE post_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newReviewText);
            statement.setTimestamp(2, newDate);
            statement.setInt(3, reviewId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePost(int postId) {
        String query = "DELETE FROM user_posts WHERE post_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
