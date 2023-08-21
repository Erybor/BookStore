import dao.AuthorDAO;
import dao.BookDAO;
import dao.ConnectionManager;
import model.Author;
import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookDAOTest {

    private static BookDAO bookDAO;
    private static AuthorDAO authorDAO;
    private static Connection testConnection;

    @BeforeEach
    void setUp() {
        testConnection = ConnectionManager.getTestConnection();
        DatabaseSetup databaseSetup = new DatabaseSetup(testConnection);
        databaseSetup.setupDatabase();
        bookDAO = new BookDAO(testConnection);
        authorDAO = new AuthorDAO(testConnection);
    }

    @Test
    void testAddBook() {
        Book book = new Book("Narnia", "author", 1, "description", 5, new ArrayList<>(List.of("Fantasy")), 2020, "coverUrl");
        bookDAO.addBook(book);
        Book fetchedBook = bookDAO.getBookById(book.getId());
        assertEquals(book.getTitle(), fetchedBook.getTitle());
        assertEquals(book.getAuthor(), fetchedBook.getAuthor());
        assertEquals(book.getId(), fetchedBook.getId());
        assertEquals(book.getDescription(), fetchedBook.getDescription());
        assertEquals(book.getRating(), fetchedBook.getRating());
    }

    @Test
    void testGetBookById() {
        Book book = new Book("Narnia", "author", 1, "description", 5, new ArrayList<>(List.of("Fantasy")), 2020, "coverUrl");
        bookDAO.addBook(book);
        Book fetchedBook = bookDAO.getBookById(book.getId());
        assertEquals(book.getTitle(), fetchedBook.getTitle());
        assertEquals(book.getAuthor(), fetchedBook.getAuthor());
        assertEquals(book.getId(), fetchedBook.getId());
        assertEquals(book.getDescription(), fetchedBook.getDescription());
        assertEquals(book.getRating(), fetchedBook.getRating());
    }

    @Test
    void testDeleteBook() {
        Book book = new Book("Narnia", "author", 1, "description", 5, new ArrayList<>(List.of("Fantasy")), 2020, "coverUrl");
        bookDAO.addBook(book);
        bookDAO.deleteBook(book.getId());
        Book fetchedBook = bookDAO.getBookById(book.getId());
        assertEquals(null, fetchedBook);
    }

    @Test
    void testGetBooksByCategories() {
        Book book1 = new Book("Narnia", "author", 1, "description", 5, new ArrayList<>(List.of("Fantasy", "Classic")), 2020, "coverUrl");
        Book book2 = new Book("Lord of the rings", "Tolkien", 2, "description", 5, new ArrayList<>(List.of("Fantasy", "Classic")), 1920, "coverUrl");
        Book book3 = new Book("Book3", "Author", 2, "description", 5, new ArrayList<>(List.of("Horror", "Classic")), 2018, "coverUrl");

        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);


        List<Book> fetchedBooks = bookDAO.getBooksByCategory("Fantasy");
        assertEquals(2, fetchedBooks.size());
        assertEquals(book1.getTitle(), fetchedBooks.get(0).getTitle());

        List<Book> fetchedBooks2 = bookDAO.getBooksByCategory("Classic");
        assertEquals(3, fetchedBooks2.size());

        List<Book> books = bookDAO.getBooksByCategories(new ArrayList<>(List.of("Fantasy", "Classic")));
        assertEquals(2, books.size());
        assertEquals("Narnia", books.get(0).getTitle());
        assertEquals("Lord of the rings", books.get(1).getTitle());
        assertEquals(1920, books.get(1).getYear());
        assertEquals(2020, books.get(0).getYear());
        assertEquals("Tolkien", books.get(1).getAuthor());
        assertEquals("author", books.get(0).getAuthor());
        assertEquals("description", books.get(0).getDescription());
        assertEquals(5, books.get(1).getRating());
        assertEquals("coverUrl", books.get(0).getCoverUrl());

        List<String> genres = bookDAO.getAllCategories();
        assertEquals(3, genres.size());

    }

    @Test
    void testGetBooksByAuthor() {
        Author author = new Author(1, "Tolkien", "coverUrl");
        authorDAO.addAuthor(author);

        Book book1 = new Book("Lord of the rings", "Tolkien", author.getId(), "description", 5, new ArrayList<>(List.of("Fantasy", "Classic")), 1920, "coverUrl");
        Book book2 = new Book("Silmarilion", "Tolkien", author.getId(), "cool book", 5, new ArrayList<>(List.of("Horror", "Classic")), 2018, "coverUrl");

        Book book3 = new Book("Narnia", "author", 3, "description", 5, new ArrayList<>(List.of("Fantasy", "Classic")), 2020, "coverUrl");
        Book book4 = new Book("Book3", "Author", 2, "description", 5, new ArrayList<>(List.of("Horror", "Classic")), 2018, "coverUrl");

        bookDAO.addBook(book1);
        bookDAO.addBook(book2);
        bookDAO.addBook(book3);
        bookDAO.addBook(book4);


        List<Book> fetchedBooks = bookDAO.getBooksByAuthor(author.getId());
        assertEquals(2, fetchedBooks.size());
        assertEquals(book1.getTitle(), fetchedBooks.get(0).getTitle());
        assertEquals(book2.getTitle(), fetchedBooks.get(1).getTitle());

    }

}
