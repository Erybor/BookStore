import dao.ConnectionManager;
import dao.FollowerDAO;
import model.Follow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

public class FollowerDAOTest {
    private static FollowerDAO followerDAO;
    private static Connection testConnection;

    @BeforeEach
    void setUp() {
        testConnection = ConnectionManager.getTestConnection();
        DatabaseSetup databaseSetup = new DatabaseSetup(testConnection);
        databaseSetup.setupDatabase();
        followerDAO = new FollowerDAO(testConnection);
    }

    @Test
    void testAddFollower() {
        Follow follow = new Follow(1, 2);
        followerDAO.addFollower(follow);
        assertTrue(followerDAO.isFollowing(1, 2));
    }

    @Test
    void testIsFollowing() {
        Follow follow = new Follow(1, 3);
        followerDAO.addFollower(follow);
        assertTrue(followerDAO.isFollowing(1, 3));
        assertFalse(followerDAO.isFollowing(2, 3));
    }

    @Test
    void testRemoveFollower() {
        Follow follow = new Follow(1, 4);
        followerDAO.addFollower(follow);
        assertTrue(followerDAO.isFollowing(1, 4));

        followerDAO.removeFollower(1, 4);
        assertFalse(followerDAO.isFollowing(1, 4));
    }

    @Test
    void testGetFollowers() {
        followerDAO.addFollower(new Follow(1, 5));
        followerDAO.addFollower(new Follow(2, 5));
        followerDAO.addFollower(new Follow(3, 5));

        List<Follow> followers = followerDAO.getFollowers(5);

        assertNotNull(followers);
        assertEquals(3, followers.size());

        assertTrue(containsFollow(followers, 1, 5));
        assertTrue(containsFollow(followers, 2, 5));
        assertTrue(containsFollow(followers, 3, 5));
        assertFalse(containsFollow(followers, 4, 5));
    }

    private boolean containsFollow(List<Follow> followers, int followerId, int followingId) {
        for (Follow follow : followers) {
            if (follow.getFollowerId() == followerId && follow.getFollowingId() == followingId) {
                return true;
            }
        }
        return false;
    }

}
