package controller.servlet;

import dao.FollowerDAO;
import dao.UserDAO;
import model.Follow;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "DisplayUserFollowers", value = "/followers/*")
public class DisplayUserFollowersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HEYY WE'RE HEREE");

        String requestURI = req.getRequestURI();
        int reqUserId = Integer.parseInt(requestURI.substring(requestURI.lastIndexOf("/") + 1));

        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDAO");
        FollowerDAO followerDAO = (FollowerDAO) req.getServletContext().getAttribute("followerDAO");


        System.out.println("USER ID: " + reqUserId);

        User user = userDAO.getUserById(reqUserId);
        List<Follow> followers = followerDAO.getFollowers(reqUserId);

        req.setAttribute("followers", followers);
        req.setAttribute("userId", reqUserId);
        req.setAttribute("user", user);

        System.out.println("FOLLOWERS: " + followers);

        req.getRequestDispatcher("/followers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
