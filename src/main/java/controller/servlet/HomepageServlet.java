package controller.servlet;


import dao.BookDAO;
import dao.UserPostDAO;
import model.UserPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomepageServlet", urlPatterns = "/homepage")
public class HomepageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPostDAO postDAO = (UserPostDAO) request.getServletContext().getAttribute("postDAO");

        List<UserPost> posts = postDAO.getFollowerPosts((int) request.getSession().getAttribute("userId"));
        List<UserPost> myPosts = postDAO.getPostByUserId((int) request.getSession().getAttribute("userId"));

        posts.addAll(myPosts);

        // Sort posts by date created
        posts.sort((post1, post2) -> post2.getDateCreated().compareTo(post1.getDateCreated()));

        request.setAttribute("posts", posts);
        request.getRequestDispatcher("homepage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle posting a new user post
        super.doPost(request, response);
    }
}


