import static org.junit.jupiter.api.Assertions.*;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testConstructorWithThreeArgs() {
        User createdUser = new User("testUsername", "testPassword", "test@email.com");

        assertEquals("testUsername", createdUser.getUsername());
        assertEquals("testPassword", createdUser.getPassword());
        assertEquals("test@email.com", createdUser.getEmail());
    }

    @Test
    void testConstructorWithFourArgs() {
        User createdUser = new User(1, "testUsername", "testPassword", "test@email.com");

        assertEquals(1, createdUser.getId());
        assertEquals("testUsername", createdUser.getUsername());
        assertEquals("testPassword", createdUser.getPassword());
        assertEquals("test@email.com", createdUser.getEmail());
    }

    @Test
    void testGetAndSetUserId() {
        int id = 2;
        user.setUserId(id);
        assertEquals(id, user.getId());
    }

    @Test
    void testGetAndSetUsername() {
        String username = "newUsername";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    void testGetAndSetPassword() {
        String password = "newPassword";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    void testGetAndSetEmail() {
        String email = "new@email.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    void testGetAndSetName() {
        String name = "John";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    void testGetAndSetLastName() {
        String lastName = "Doe";
        user.setLastName(lastName);
        assertEquals(lastName, user.getLastName());
    }

    @Test
    void testGetAndSetGender() {
        String gender = "Male";
        user.setGender(gender);
        assertEquals(gender, user.getGender());
    }

    @Test
    void testGetAndSetProfilePicture() {
        String profilePicture = "path/to/image.jpg";
        user.setProfilePicture(profilePicture);
        assertEquals(profilePicture, user.getProfilePicture());
    }

}

