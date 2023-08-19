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

@WebServlet(name = "DisplayReviewServlet", value = "/review/*")
public class DisplayReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DisplayReviewServlet: doGet");

        String requestURI = req.getRequestURI();
        String reviewId = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        System.out.println("REVIEW ID:" + reviewId);

        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");
        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");
        int userId = (int) req.getSession().getAttribute("userId");

        Review review = reviewDAO.getReviewById(Integer.parseInt(reviewId));
//        int bookId = Integer.parseInt(req.getParameter("bookId"));
        System.out.println("REVIEW:" + review);

        int reviewUserId = review.getUserId();

        // Add checks to make sure the user is authorized to edit the review
        // Check if the user is the owner of the review

        if (reviewUserId != userId) {
            resp.sendError(403, "You are not authorized to edit this review");
            return;
        }


        System.out.println("REVIEW ID:" + reviewId);
        Book book = bookDAO.getBookById(review.getBookId());

        req.setAttribute("reviewId", reviewId);
        req.setAttribute("review", review);
        req.setAttribute("book", book);


        req.getRequestDispatcher("/editReview.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DisplayReviewServlet: doPost");
        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");
    }

}
