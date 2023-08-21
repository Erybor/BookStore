import static org.junit.jupiter.api.Assertions.*;

import model.UserPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class UserPostTest {

    private UserPost userPost;

    @BeforeEach
    void setUp() {
        userPost = new UserPost();
    }

    @Test
    void testConstructorWithThreeArgs() {
        Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
        UserPost post = new UserPost(1, "Test Content", dateCreated);

        assertEquals(1, post.getUserId());
        assertEquals("Test Content", post.getContent());
        assertEquals(dateCreated, post.getDateCreated());
    }

    @Test
    void testConstructorWithFourArgs() {
        Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
        UserPost post = new UserPost(1, 2, "Test Content", dateCreated);

        assertEquals(1, post.getPostId());
        assertEquals(2, post.getUserId());
        assertEquals("Test Content", post.getContent());
        assertEquals(dateCreated, post.getDateCreated());
    }

    @Test
    void testGetAndSetPostId() {
        int postId = 1;
        userPost.setPostId(postId);
        assertEquals(postId, userPost.getPostId());
    }

    @Test
    void testGetAndSetUserId() {
        int userId = 2;
        userPost.setUserId(userId);
        assertEquals(userId, userPost.getUserId());
    }

    @Test
    void testGetAndSetContent() {
        String content = "This is a test content.";
        userPost.setContent(content);
        assertEquals(content, userPost.getContent());
    }

    @Test
    void testGetDateCreated() {
        Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
        userPost = new UserPost(1, "Test", dateCreated);
        assertEquals(dateCreated, userPost.getDateCreated());
    }


    @Test
    void testToString() {
        Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
        userPost = new UserPost(1, 2, "Test", dateCreated);
        String expected = "UserPost{" +
                "postId=" + 1 +
                ", userId=" + 2 +
                ", content='" + "Test" + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
        assertEquals(expected, userPost.toString());
    }

}
