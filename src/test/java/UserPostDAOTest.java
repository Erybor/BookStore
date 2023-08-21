import dao.ConnectionManager;
import dao.FollowerDAO;
import dao.UserPostDAO;
import model.Follow;
import model.UserPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;

public class UserPostDAOTest {
    private static UserPostDAO userPostDAO;
    private static FollowerDAO followerDAO;
    private static Connection testConnection;


    @BeforeEach
    void setUp() {
        testConnection = ConnectionManager.getTestConnection();
        DatabaseSetup databaseSetup = new DatabaseSetup(testConnection);
        databaseSetup.setupDatabase();
        userPostDAO = new UserPostDAO(testConnection);
        followerDAO = new FollowerDAO(testConnection);
    }

    @Test
    void testAddAndGetPostById() {
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        UserPost newPost = new UserPost(1, "This is a test post!", currentTimestamp);

        userPostDAO.addUserPost(newPost);

        UserPost fetchedPost = userPostDAO.getPostById(newPost.getPostId());
        assertNotNull(fetchedPost);
        assertEquals(1, fetchedPost.getUserId());
        assertEquals("This is a test post!", fetchedPost.getContent());


        // Compare without considering milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expectedDateStr = sdf.format(currentTimestamp);
        String fetchedDateStr = sdf.format(fetchedPost.getDateCreated());
        assertEquals(expectedDateStr, fetchedDateStr);

    }

    @Test
    void testGetPostByUserId() {
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        UserPost post1 = new UserPost(1, "Test post 1", currentTimestamp);
        UserPost post2 = new UserPost(1, "Test post 2", currentTimestamp);
        userPostDAO.addUserPost(post1);
        userPostDAO.addUserPost(post2);

        List<UserPost> posts = userPostDAO.getPostByUserId(1);

        assertNotNull(posts);
        assertEquals(2, posts.size());
        assertTrue(posts.stream().anyMatch(p -> p.getContent().equals("Test post 1")));
        assertTrue(posts.stream().anyMatch(p -> p.getContent().equals("Test post 2")));
    }

    @Test
    void testGetFollowerPosts() {
        // Assuming user 1 follows user 2 and user 3

        Follow follow1 = new Follow(1, 2);
        Follow follow2 = new Follow(1, 3);
        Follow follow3 = new Follow(2, 3);
        followerDAO.addFollower(follow1);
        followerDAO.addFollower(follow2);
        followerDAO.addFollower(follow3);



        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        UserPost postUser2 = new UserPost(2, "User 2 post", currentTimestamp);
        UserPost postUser3 = new UserPost(3, "User 3 post", currentTimestamp);

        userPostDAO.addUserPost(postUser2);
        userPostDAO.addUserPost(postUser3);

        List<UserPost> posts = userPostDAO.getFollowerPosts(1);

        assertEquals(2, posts.size());
        assertTrue(posts.stream().anyMatch(p -> p.getContent().equals("User 2 post")));
        assertTrue(posts.stream().anyMatch(p -> p.getContent().equals("User 3 post")));
    }

    @Test
    void testDeletePost() {
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        UserPost post = new UserPost(1, "Post to delete", currentTimestamp);
        userPostDAO.addUserPost(post);

        userPostDAO.deletePost(post.getPostId());

        assertNull(userPostDAO.getPostById(post.getPostId()));
    }

}
