package controller.servlet;

import dao.BookDAO;
import dao.ReviewDAO;
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
        System.out.println("DisplayReviewServlet: doPost");

        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");




        int reviewId = Integer.parseInt(req.getParameter("reviewId"));
        System.out.println("REVIEW ID:" + reviewId);
//        int userId = Integer.parseInt(req.getParameter("userId"));
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        System.out.println("BOOK ID:" + bookId);
        int rating = Integer.parseInt(req.getParameter("rating"));
        String reviewText = req.getParameter("reviewText");


        int reviewUserId = reviewDAO.getReviewById(reviewId).getUserId();
        int currUserId = (int) req.getSession().getAttribute("userId");

        if (reviewUserId != currUserId) {
            resp.sendError(403, "You are not authorized to edit this review");
            return;
        }


        reviewDAO.updateReview(reviewId, reviewText, rating);

        resp.sendRedirect("/book/" + bookId);

    }

}
