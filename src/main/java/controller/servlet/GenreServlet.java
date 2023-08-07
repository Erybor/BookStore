package controller.servlet;

import dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/genre/*")
public class GenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String genre = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        BookDAO bookDAO = (BookDAO) req.getServletContext().getAttribute("bookDAO");
        System.out.println("GENRE:" + genre);

        // TODO BOOKS BY GENRE AND PROCESS HERE

        resp.sendRedirect("/genre.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}
