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

@WebServlet("/SearchGenreServlet")
public class SearchGenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");
        String[] genres = req.getParameter("genre").split(",");

        req.setAttribute("filteredBooks", bookDAO.getBooksByCategories(List.of(genres)));

        req.getRequestDispatcher("genre.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}
