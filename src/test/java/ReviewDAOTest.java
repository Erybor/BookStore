import dao.BookDAO;
import dao.ConnectionManager;
import dao.ReviewDAO;
import dao.UserDAO;
import model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewDAOTest {

    private static ReviewDAO reviewDAO;
    private static Connection testConnection;

    @BeforeEach
    void setUp() {
        testConnection = ConnectionManager.getTestConnection();
        DatabaseSetup databaseSetup = new DatabaseSetup(testConnection);
        databaseSetup.setupDatabase();
        reviewDAO = new ReviewDAO(testConnection);
    }

    @Test
    void testAddReview() {
        Review review = new Review(1, 1, "nice book", 5);
        reviewDAO.addReview(review);

        System.out.println(review.getReviewId());
        Review fetchedReview = reviewDAO.getReviewById(review.getReviewId());

        assertEquals(review.getReviewText(), fetchedReview.getReviewText());
        assertEquals(review.getRatingValue(), fetchedReview.getRatingValue());
    }

    @Test
    void testGetReviewsForBook() {
        Review review = new Review(1, 1, "nice book", 5);
        reviewDAO.addReview(review);


        review = new Review(2, 1, "meh", 2);
        reviewDAO.addReview(review);

        review = new Review(3, 1, "Woww you have to see the ending", 5);
        reviewDAO.addReview(review);

        List<Review> reviews = reviewDAO.getReviewsForBook(1);
        assertEquals(3, reviews.size());
        assertEquals("nice book", reviews.get(0).getReviewText());
        assertEquals("meh", reviews.get(1).getReviewText());
        assertEquals("Woww you have to see the ending", reviews.get(2).getReviewText());
        assertEquals(4, (reviews.get(2).getRatingValue() + reviews.get(1).getRatingValue() + reviews.get(0).getRatingValue()) / 3 );


        // ... other assertions
    }

    @Test
    void testGetUserReviewForBook() {
        Review review = new Review(1, 1, "nice book", 5);
        reviewDAO.addReview(review);

        Review fetchedReview = reviewDAO.getUserReviewForBook(1, 1);
        assertEquals("nice book", fetchedReview.getReviewText());
        assertEquals(5, fetchedReview.getRatingValue());
        assertEquals(1, fetchedReview.getUserId());
        assertEquals(1, fetchedReview.getBookId());
    }

    @Test
    void testUpdateReview() {

        Review review = new Review(1, 1, "nice book", 5);
        reviewDAO.addReview(review);

        reviewDAO.updateReview(review.getReviewId(), "Updated Text", 5);
        Review updatedReview = reviewDAO.getReviewById(review.getReviewId());

        assertEquals("Updated Text", updatedReview.getReviewText());
        assertEquals(5, updatedReview.getRatingValue());
        assertEquals(1, updatedReview.getUserId());
        assertEquals(1, updatedReview.getBookId());

    }


    @Test
    void testGetReviewsForUser() {
        Review review = new Review(1, 1, "nice book", 5);
        reviewDAO.addReview(review);

        review = new Review(1, 2, "meh", 2);
        reviewDAO.addReview(review);

        review = new Review(1, 3, "Woww you have to see the ending", 5);
        reviewDAO.addReview(review);


        List<Review> reviews = reviewDAO.getReviewsForUser(1);
        assertEquals(3, reviews.size());
        assertEquals("nice book", reviews.get(0).getReviewText());
        assertEquals("meh", reviews.get(1).getReviewText());

    }

    @Test
    void testGetReviewCountForBook() {

        Review review = new Review(1, 1, "nice book", 5);
        reviewDAO.addReview(review);

        review = new Review(2, 1, "meh", 2);
        reviewDAO.addReview(review);

        int count = reviewDAO.getReviewCountForBook(1);
        assertEquals(2, count);

    }

}