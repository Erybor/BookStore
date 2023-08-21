import dao.ConnectionManager;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private static UserDAO userDAO;
    private static Connection testConnection;

    @BeforeEach
    void setUp() {
        testConnection = ConnectionManager.getTestConnection();
        DatabaseSetup databaseSetup = new DatabaseSetup(testConnection);
        databaseSetup.setupDatabase();
        userDAO = new UserDAO(testConnection);
    }

    @Test
    void testAddAndGetUser() {
        User newUser = new User();
        newUser.setUsername("testUser");
        newUser.setPassword("testPassword");
        newUser.setEmail("testUser@email.com");

        userDAO.addUser(newUser);

        User fetchedUser = userDAO.getUser("testUser");
        assertNotNull(fetchedUser);
        assertEquals("testUser", fetchedUser.getUsername());
        // Note: the password is likely hashed, so you might want to avoid direct comparison
        assertEquals("testUser@email.com", fetchedUser.getEmail());
    }

    @Test
    void testGetWrongUser() {
        User fetchedUser = userDAO.getUser("wrongUser");
        assertNull(fetchedUser);
    }

    @Test
    void testGetUserById() {
        User newUser = new User();
        newUser.setUsername("testUserById");
        newUser.setPassword("testPassword");
        newUser.setEmail("testUserById@email.com");

        userDAO.addUser(newUser);

        User fetchedUser = userDAO.getUserById(newUser.getId());
        assertNotNull(fetchedUser);
        assertEquals("testUserById", fetchedUser.getUsername());
        assertEquals("testUserById@email.com", fetchedUser.getEmail());
    }

    @Test
    void testUpdateUserProfile() {
        User newUser = new User();
        newUser.setUsername("testUpdateUser");
        newUser.setPassword("testPassword");
        newUser.setEmail("testUpdateUser@email.com");

        userDAO.addUser(newUser);

        userDAO.updateUserProfile(newUser.getId(), "John", "Doe", "Male", "path/to/profilePicture.jpg");

        User updatedUser = userDAO.getUserById(newUser.getId());
        assertNotNull(updatedUser);
        assertEquals("John", updatedUser.getName());
        assertEquals("Doe", updatedUser.getLastName());
        assertEquals("Male", updatedUser.getGender());
        assertEquals("path/to/profilePicture.jpg", updatedUser.getProfilePicture());
    }

}
