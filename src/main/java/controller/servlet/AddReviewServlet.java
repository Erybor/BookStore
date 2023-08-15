package controller.servlet;


import dao.ReviewDAO;
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

        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");

        List<Review> reviews = reviewDAO.getReviewsForBook(bookId);
        req.setAttribute("reviews", reviews);
        req.setAttribute("bookId", bookId);

        // Check if the user has already reviewed this book
        Review review = reviewDAO.getUserReviewForBook(userId, bookId);

        // Don't allow the user to review the same book twice
        if (review != null) {
            System.out.println("You have already reviewed this book!, please edit your review instead");

            req.getSession().setAttribute("reviewError", true);
//            req.setAttribute("reviewError", true);
            resp.sendRedirect("/book/" + bookId);

//            req.getRequestDispatcher("/book/" + bookId).forward(req, resp);
            return;
        }

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