import static org.junit.jupiter.api.Assertions.*;

import model.Book;
import model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @Test
    void testConstructorWithEightArgs() {
        List<String> genres = Arrays.asList("Fiction", "Adventure");
        Book createdBook = new Book("Title", "Author", 1, "Description", 4.5, genres, 2023, "coverUrl");

        assertEquals("Title", createdBook.getTitle());
        assertEquals("Author", createdBook.getAuthor());
        assertEquals(1, createdBook.getAuthorId());
        assertEquals("Description", createdBook.getDescription());
        assertEquals(4.5, createdBook.getRating());
        assertEquals(genres, createdBook.getGenres());
        assertEquals(2023, createdBook.getYear());
        assertEquals("coverUrl", createdBook.getCoverUrl());
    }

    @Test
    void testConstructorWithNineArgs() {
        List<String> genres = Arrays.asList("Fiction", "Adventure");
        Book createdBook = new Book(2, "Title", "Author", 1, "Description", 4.5, genres, 2023, "coverUrl");

        assertEquals(2, createdBook.getId());
        assertEquals("Title", createdBook.getTitle());
        assertEquals("Author", createdBook.getAuthor());
        assertEquals(1, createdBook.getAuthorId());
        assertEquals("Description", createdBook.getDescription());
        assertEquals(4.5, createdBook.getRating());
        assertEquals(genres, createdBook.getGenres());
        assertEquals(2023, createdBook.getYear());
        assertEquals("coverUrl", createdBook.getCoverUrl());
    }

    @Test
    void testGetAndSetId() {
        int id = 5;
        book.setId(id);
        assertEquals(id, book.getId());
    }

    @Test
    void testGetAndSetTitle() {
        String title = "Test Title";
        book.setTitle(title);
        assertEquals(title, book.getTitle());
    }

    @Test
    void testGetAndSetAuthorId() {
        int authorId = 3;
        book.setAuthorId(authorId);
        assertEquals(authorId, book.getAuthorId());
    }

    @Test
    void testGetAndSetAuthor() {
        String author = "Test Author";
        book.setAuthor(author);
        assertEquals(author, book.getAuthor());
    }

    @Test
    void testGetAndSetDescription() {
        String description = "Test Description";
        book.setDescription(description);
        assertEquals(description, book.getDescription());
    }

    @Test
    void testGetAndSetRating() {
        double rating = 4.8;
        book.setRating(rating);
        assertEquals(rating, book.getRating());
    }

    @Test
    void testGetAndSetGenres() {
        List<String> genres = Arrays.asList("Genre1", "Genre2");
        book.setGenres(genres);
        assertEquals(genres, book.getGenres());
    }

    @Test
    void testGetAndSetYear() {
        int year = 2023;
        book.setYear(year);
        assertEquals(year, book.getYear());
    }

    @Test
    void testGetAndSetCoverUrl() {
        String coverUrl = "testCoverUrl";
        book.setCoverUrl(coverUrl);
        assertEquals(coverUrl, book.getCoverUrl());
    }

    @Test
    void testGetAndSetReviews() {
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        book.setReviews(reviews);
        assertEquals(reviews, book.getReviews());
    }

    @Test
    void testToString() {
        List<String> genres = Arrays.asList("Fiction", "Adventure");
        Book book = new Book(2, "Title", "Author", 1, "Description", 4.5, genres, 2023, "coverUrl");
        String expected = "Book{" +
                "id=" + 2 +
                ", title='" + "Title" + '\'' +
                ", author='" + "Author" + '\'' +
                ", authorId=" + 1 +
                ", description='" + "Description" + '\'' +
                ", rating=" + 4.5 +
                ", genres=" + genres +
                ", year=" + 2023 +
                ", coverUrl='" + "coverUrl" + '\'' +
                '}';
        assertEquals(expected, book.toString());
    }


}
