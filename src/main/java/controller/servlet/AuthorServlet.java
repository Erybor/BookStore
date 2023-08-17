package controller.servlet;

import dao.AuthorDAO;
import dao.BookDAO;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/author/*")
public class AuthorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String authorId = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        AuthorDAO authorDAO = (AuthorDAO) req.getServletContext().getAttribute("authorDAO");
        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");
        List<Book> books = bookDAO.getBooksByAuthor(Integer.parseInt(authorId));

        System.out.println("authorDAO:" + authorDAO);
        Author author = authorDAO.getAuthor(Integer.parseInt(authorId));
        System.out.println("Author:" + author);
        req.setAttribute("author", author);
        req.setAttribute("authorId", authorId);
        req.setAttribute("authorBooks", books);


        req.getRequestDispatcher("/author.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}