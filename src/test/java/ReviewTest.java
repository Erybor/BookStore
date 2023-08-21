import static org.junit.jupiter.api.Assertions.*;

import model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReviewTest {

    private Review review;

    @BeforeEach
    void setUp() {
        review = new Review();
    }

    @Test
    void testGetAndSetReviewId() {
        int reviewId = 1;
        review.setReviewId(reviewId);
        assertEquals(reviewId, review.getReviewId());
    }

    @Test
    void testGetAndSetUserId() {
        int userId = 2;
        review.setUserId(userId);
        assertEquals(userId, review.getUserId());
    }

    @Test
    void testGetAndSetBookId() {
        int bookId = 3;
        review.setBookId(bookId);
        assertEquals(bookId, review.getBookId());
    }

    @Test
    void testGetAndSetReviewText() {
        String reviewText = "This is a test review.";
        review.setReviewText(reviewText);
        assertEquals(reviewText, review.getReviewText());
    }

    @Test
    void testGetAndSetRatingValue() {
        int ratingValue = 4;
        review.setRatingValue(ratingValue);
        assertEquals(ratingValue, review.getRatingValue());
    }

    @Test
    void testConstructor() {
        Review review = new Review(1, 2, "This is a test review.", 4);
        assertEquals(1, review.getUserId());
        assertEquals(2, review.getBookId());
        assertEquals("This is a test review.", review.getReviewText());
        assertEquals(4, review.getRatingValue());
    }

}