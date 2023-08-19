package controller.servlet;

import dao.FollowerDAO;
import dao.ReviewDAO;
import dao.UserDAO;
import model.Follow;
import model.Review;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfilePageServlet", value = "/profile")
public class ProfilePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ProfilePageServlet: doGet");
        System.out.println(req.getSession().getAttribute("username"));

        int userId = (int) req.getSession().getAttribute("userId");
        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDAO");
        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");
        FollowerDAO followerDAO = (FollowerDAO) req.getServletContext().getAttribute("followerDAO");

        List<Review> reviews = reviewDAO.getReviewsForUser(userId);
        List<Follow> follows = followerDAO.getFollowers(userId);
        User user = userDAO.getUserById(userId);

        req.setAttribute("user", user);
        req.setAttribute("followerCount", follows.size());
        req.setAttribute("reviews", reviews);

        // Forward to the profile JSP
//        resp.sendRedirect("/profile.jsp");
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ProfilePageServlet: doPost");
        super.doPost(req, resp);
    }
}
