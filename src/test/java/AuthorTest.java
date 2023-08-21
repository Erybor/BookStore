import static org.junit.jupiter.api.Assertions.*;

import model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthorTest {

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author();
    }

    @Test
    void testConstructorWithTwoArgs() {
        Author createdAuthor = new Author("Test Name", "Test Info");

        assertEquals("Test Name", createdAuthor.getName());
        assertEquals("Test Info", createdAuthor.getInfo());
    }

    @Test
    void testConstructorWithThreeArgs() {
        Author createdAuthor = new Author(1, "Test Name", "Test Info");

        assertEquals(1, createdAuthor.getId());
        assertEquals("Test Name", createdAuthor.getName());
        assertEquals("Test Info", createdAuthor.getInfo());
    }

    @Test
    void testGetAndSetName() {
        String name = "New Test Name";
        author.setName(name);
        assertEquals(name, author.getName());
    }

    @Test
    void testGetAndSetInfo() {
        String info = "New Test Info";
        author.setInfo(info);
        assertEquals(info, author.getInfo());
    }

    @Test
    void testGetAndSetId() {
        int id = 3;
        author.setId(id);
        assertEquals(id, author.getId());
    }

    @Test
    void testToString() {
        Author author = new Author(1, "Test Name", "Test Info");
        assertEquals("Author{info='Test Info', name='Test Name'}", author.toString());
    }
}
