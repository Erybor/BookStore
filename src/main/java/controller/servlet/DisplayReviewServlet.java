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
        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");
        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");

        int userId = Integer.parseInt(req.getParameter("userId"));
        int bookId = Integer.parseInt(req.getParameter("bookId"));



        System.out.println("REVIEW ID:" + reviewId);
        Review review = reviewDAO.getUserReviewForBook(userId, bookId);
        Book book = bookDAO.getBookById(bookId);

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
