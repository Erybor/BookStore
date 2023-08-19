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

@WebServlet(name = "EditReviewServlet", value = "/EditReviewServlet")
public class EditReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DisplayReviewServlet: doGet");
        super.doGet(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EditReviewServlet: doPost");

        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");
        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");

        int reviewId = Integer.parseInt(req.getParameter("reviewId"));
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        int newRating = Integer.parseInt(req.getParameter("rating")); // New rating from the edited review

        Review oldReview = reviewDAO.getReviewById(reviewId);
        int oldRating = oldReview.getRatingValue(); // Old rating from the original review

        int reviewUserId = oldReview.getUserId();
        int currUserId = (int) req.getSession().getAttribute("userId");

        if (reviewUserId != currUserId) {
            resp.sendError(403, "You are not authorized to edit this review");
            return;
        }

        // Update the review
        String newReviewText = req.getParameter("reviewText");
        reviewDAO.updateReview(reviewId, newReviewText, newRating);

        // Update Book Rating
        int reviewCount = reviewDAO.getReviewCountForBook(bookId);
        Book book = bookDAO.getBookById(bookId);
        double bookRating = book.getRating();

        // Calculate new average rating by subtracting the old rating and adding the new rating
        double newBookRating = ((bookRating * reviewCount) - oldRating + newRating) / reviewCount;

        bookDAO.updateBookRating(bookId, newBookRating);

        resp.sendRedirect("/book/" + bookId);
    }

}
