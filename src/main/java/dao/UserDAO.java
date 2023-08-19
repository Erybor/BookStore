package dao;

import model.User;
import util.Hasher;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public int addUser(User user) {
        int generatedUserId = -1;  // Initialize with a default value

        try {
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, Hasher.generateHash(user.getPassword()));
            statement.setString(3, user.getEmail());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedUserId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedUserId;
    }

    public void addUser(String username, String password, String email) {
        try {
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, Hasher.generateHash(password));
            statement.setString(3, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Retrieve a user by username
    public User getUser(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                System.out.println(user);
                return extractUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int userID) {
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                System.out.println(user);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public int getUserFollowers(int userID) throws SQLException {
        try {
            String sql = "SELECT u.* FROM users u INNER JOIN followers f ON u.user_id = f.follower_id WHERE f.following_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            List<User> followers = new ArrayList<>();
            while (resultSet.next()) {
                User follower = extractUserFromResultSet(resultSet);
                followers.add(follower);
            }
            return followers.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setGender(resultSet.getString("gender"));
        user.setProfilePicture(resultSet.getString("profile_picture"));

        return user;
    }

    public void updateUserProfile(int userId, String name, String lastName, String gender, String profilePicture) {
        try {
            System.out.println("Updating user profile");
            String sql = "UPDATE users SET name = ?, last_name = ?, gender = ?, profile_picture = ? WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, gender);
            statement.setString(4, profilePicture);
            statement.setInt(5, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
