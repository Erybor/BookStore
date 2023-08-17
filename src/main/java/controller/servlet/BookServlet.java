package controller.servlet;

import dao.BookDAO;
import dao.ReviewDAO;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book/*")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String bookId = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");
        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");

        System.out.println("bookID:" + bookId);
        Book book = bookDAO.getBookById(Integer.parseInt(bookId));
        System.out.println("CURRBOOK:" + book);
        req.setAttribute("book", book);
        req.setAttribute("bookId", bookId);
        req.setAttribute("reviews", reviewDAO.getReviewsForBook(Integer.parseInt(bookId)));


        req.getRequestDispatcher("/BookPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");

        System.out.println("BookServlet: doPost");
        System.out.println("bookID:" + bookId);

        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");
        Book book = bookDAO.getBookById(bookId);
        req.setAttribute("book", book);
        req.setAttribute("reviews", reviewDAO.getReviewsForBook(bookId));


        req.getRequestDispatcher("/BookPage.jsp").forward(req, resp);
    }
}