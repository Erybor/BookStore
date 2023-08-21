import model.Follow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FollowTest {


    @Test
    void followTest() {
        Follow follow = new Follow(1, 2);
        assertEquals(1, follow.getFollowerId());
        assertEquals(2, follow.getFollowingId());
    }

    @Test
    void testToString() {
        Follow follow = new Follow(1, 2);
        assertEquals("Follow{followerId=1, followingId=2}", follow.toString());
    }

}
