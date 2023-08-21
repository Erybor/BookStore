package controller.servlet;

import dao.BookDAO;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/genre/*")
public class GenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String genre = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");
        System.out.println("GENRE:" + genre);

        // There are %20 s in the genre name, so we replace them with spaces
        genre = genre.replace("%20", " ");

        req.setAttribute("genres", List.of(genre));

        // TODO BOOKS BY GENRE AND PROCESS HERE

        List<Book> books = bookDAO.getBooksByCategories(List.of(genre));

        req.setAttribute("filteredBooks", books);
        System.out.println("WIGNEBI:" + books);
        req.getRequestDispatcher("/genre.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}
