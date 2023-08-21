package controller.servlet;


import dao.BookDAO;
import dao.ReviewDAO;
import model.Book;
import model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("WE'RE IN THE REVIEW SERVLET");

        int userId = (int) req.getSession().getAttribute("userId");
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String reviewText = req.getParameter("review");
        int ratingValue = Integer.parseInt(req.getParameter("rating"));

        // check that ratingValue is between 1 and 5
        if (ratingValue < 1 || ratingValue > 5) {
            req.getSession().setAttribute("ratingError", "Please enter a rating between 1 and 5");
            resp.sendRedirect("/book/" + bookId);
            return;
        }

        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");
        Book book = bookDAO.getBookById(bookId);

        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");
        Review review = reviewDAO.getUserReviewForBook(userId, bookId);


        List<Review> reviews = reviewDAO.getReviewsForBook(bookId);

        req.setAttribute("reviews", reviews);
        req.setAttribute("bookId", bookId);

        // Check if the user has already reviewed this book

        // Don't allow the user to review the same book twice
        if (review != null) {
            System.out.println("You have already reviewed this book!, please edit your review instead");

            req.getSession().setAttribute("reviewError", true);
            req.getSession().setAttribute("reviewId", review.getReviewId());
//            req.setAttribute("reviewError", true);,
            resp.sendRedirect("/book/" + bookId);
            return;
        }


        // Update Book Rating
        int reviewCount = reviewDAO.getReviewCountForBook(bookId);
        double bookRating = book.getRating();
        double newBookRating = ((bookRating * reviewCount) + ratingValue) / (reviewCount + 1);

        bookDAO.updateBookRating(bookId, newBookRating);


        review = new Review(userId, bookId, reviewText, ratingValue);

        // Store the review in the database using your DAO
        reviewDAO.addReview(review);

//        req.getRequestDispatcher("/book/" + bookId).forward(req, resp);
        resp.sendRedirect("/book/" + bookId);

    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doGet(httpServletRequest, httpServletResponse);
    }
}