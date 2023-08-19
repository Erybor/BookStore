package dao;

import model.Follow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowerDAO {
    private Connection connection;

    public FollowerDAO(Connection connection) {
        this.connection = connection;
    }

    public void addFollower(Follow follow) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO follows (follower_id, following_id) VALUES (?, ?)")) {
            statement.setInt(1, follow.getFollowerId());
            statement.setInt(2, follow.getFollowingId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isFollowing(int followerId, int followingId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM follows WHERE follower_id = ? AND following_id = ?")) {
            statement.setInt(1, followerId);
            statement.setInt(2, followingId);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeFollower(int followerId, int followingId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM follows WHERE follower_id = ? AND following_id = ?")) {
            statement.setInt(1, followerId);
            statement.setInt(2, followingId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Follow> getFollowers(int userId) {
        List<Follow> follows = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM follows WHERE  following_id = ?")) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int followerId = rs.getInt("follower_id");
                int followingId = rs.getInt("following_id");
                Follow follow = new Follow(followerId, followingId);
                follows.add(follow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return follows;
    }

}
