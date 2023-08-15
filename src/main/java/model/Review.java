package model;

public class Review {

    private int reviewId;
    private int userId;
    private int bookId;
    private String reviewText;
    private int ratingValue;

    public Review(int userId, int bookId, String reviewText, int ratingValue) {
        this.userId = userId;
        this.bookId = bookId;
        this.reviewText = reviewText;
        this.ratingValue = ratingValue;
    }

    public Review() {}

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

}
