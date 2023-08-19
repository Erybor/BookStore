package controller.servlet;

import dao.UserPostDAO;
import model.UserPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


@WebServlet(name = "AddPostServlet", urlPatterns = "/addPost")
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserPostDAO postDAO = (UserPostDAO) req.getServletContext().getAttribute("postDAO");

        int userId = (int) req.getSession().getAttribute("userId");
        String content = req.getParameter("content");

        Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
        UserPost post = new UserPost(userId, content, dateCreated);

        postDAO.addUserPost(post);

        resp.sendRedirect("/homepage");

    }
}
