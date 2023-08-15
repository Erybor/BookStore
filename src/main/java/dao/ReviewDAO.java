package dao;

import model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    private Connection connection;

    public ReviewDAO(Connection connection) {
        this.connection = connection;
    }

    public void addReview(Review review) {
        try {
            String sql = "INSERT INTO reviews (user_id, book_id, review_text, rating_value) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, review.getUserId());
            statement.setInt(2, review.getBookId());
            statement.setString(3, review.getReviewText());
            statement.setInt(4, review.getRatingValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Review> getReviewsForBook(int bookId) {
        List<Review> reviews = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reviews WHERE book_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Review review = extractReviewFromResultSet(resultSet);
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public Review getUserReviewForBook(int userId, int bookId) {
        try {
            String sql = "SELECT * FROM reviews WHERE user_id = ? AND book_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, bookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractReviewFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Review extractReviewFromResultSet(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setReviewId(resultSet.getInt("review_id"));
        review.setUserId(resultSet.getInt("user_id"));
        review.setBookId(resultSet.getInt("book_id"));
        review.setReviewText(resultSet.getString("review_text"));
        review.setRatingValue(resultSet.getInt("rating_value"));
        return review;
    }


    public void updateReview(int reviewId, int userId, int bookId) {
        try {
            String sql = "UPDATE reviews SET user_id = ?, book_id = ? WHERE review_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, bookId);
            statement.setInt(3, reviewId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();

        }

    }

}
