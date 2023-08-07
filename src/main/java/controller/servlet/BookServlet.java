package controller.servlet;

import dao.BookDAO;
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

        Book currBook = bookDAO.findBookById(Integer.parseInt(bookId));
        req.setAttribute("book", currBook);
        req.setAttribute("bookId", bookId);

        req.getRequestDispatcher("/BookPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}