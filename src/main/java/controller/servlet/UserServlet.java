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

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        int reqUserId = Integer.parseInt(requestURI.substring(requestURI.lastIndexOf("/") + 1));
        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDAO");
        ReviewDAO reviewDAO = (ReviewDAO) req.getServletContext().getAttribute("reviewDAO");
        FollowerDAO followerDAO = (FollowerDAO) req.getServletContext().getAttribute("followerDAO");

        List<Review> reviews = reviewDAO.getReviewsForUser(reqUserId);

        User user = userDAO.getUserById(reqUserId);

        int sessionUserId = Integer.parseInt(req.getSession().getAttribute("userId").toString());

//        if (reqUserId == sessionUserId) {
//            System.out.println("ITS THE SAME USER");
//            resp.sendRedirect("/profile");
//            return;
//        }

        List<Follow> follows = followerDAO.getFollowers(reqUserId);

        req.setAttribute("following", followerDAO.isFollowing(sessionUserId, reqUserId));
        req.setAttribute("followerCount", follows.size());


        req.setAttribute("user", user);
        req.setAttribute("reqUserId", reqUserId);
        req.setAttribute("reviews", reviews);

        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}