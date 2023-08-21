import dao.AuthorDAO;
import dao.ConnectionManager;
import model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorDAOTest {

    private AuthorDAO authorDAO;
    private static Connection testConnection;

    @BeforeEach
    void setUp() {
        testConnection = ConnectionManager.getTestConnection();

        DatabaseSetup databaseSetup = new DatabaseSetup(testConnection);
        databaseSetup.setupDatabase();

        authorDAO = new AuthorDAO(testConnection);
    }

    @Test
    void testAddAuthor() {
        Author author = new Author(1, "John Doe", "Sample Info");
        authorDAO.addAuthor(author);

        Author fetchedAuthor = authorDAO.getAuthor(1);
        assertEquals(author.getName(), fetchedAuthor.getName());
        assertEquals(author.getInfo(), fetchedAuthor.getInfo());
    }

    @Test
    void testUpdateAuthor() {
        Author initialAuthor = new Author(1, "John Doe", "Sample Info");
        authorDAO.addAuthor(initialAuthor);

        Author updatedAuthor = new Author(1, "John Doe Updated", "Updated Info");
        authorDAO.updateAuthor(updatedAuthor);

        Author fetchedAuthor = authorDAO.getAuthor(1);
        assertEquals(updatedAuthor.getName(), fetchedAuthor.getName());
        assertEquals(updatedAuthor.getInfo(), fetchedAuthor.getInfo());
    }

    @Test
    void testDeleteAuthor() {
        Author author = new Author(1, "John Doe", "Sample Info");
        authorDAO.addAuthor(author);

        authorDAO.deleteAuthor(1);
        assertNull(authorDAO.getAuthor(1));
    }

    @Test
    void testGetAllAuthors() {
        Author author1 = new Author(1, "John Doe", "Sample Info");
        Author author2 = new Author(2, "Jane Doe", "Another Info");
        authorDAO.addAuthor(author1);
        authorDAO.addAuthor(author2);

        List<Author> authors = authorDAO.getAllAuthors();
        assertEquals(2, authors.size());
    }

    @Test
    void testGetAuthorById() {
        Author author = new Author(1, "John Doe", "Sample Info");
        authorDAO.addAuthor(author);

        Author fetchedAuthor = authorDAO.getAuthor(1);
        assertNotNull(fetchedAuthor);
        assertEquals(author.getName(), fetchedAuthor.getName());
    }

    @Test
    void testGetAuthorByName() {
        Author author = new Author(1, "John Doe", "Sample Info");
        authorDAO.addAuthor(author);

        Author fetchedAuthor = authorDAO.getAuthor("John Doe");
        assertNotNull(fetchedAuthor);
        assertEquals(author.getId(), fetchedAuthor.getId());
    }

}