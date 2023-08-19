package controller.servlet;

import dao.FollowerDAO;
import model.Follow;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddFollowerServlet", value = "/AddFollowerServlet")
public class AddFollowerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FollowerDAO followerDAO = (FollowerDAO) req.getServletContext().getAttribute("followerDAO");

        int followerId = Integer.parseInt(req.getParameter("followerId"));
        int followingId = Integer.parseInt(req.getParameter("followingId"));

        Follow follow = new Follow(followerId, followingId);


        if (followerId == followingId) {
            req.getSession().setAttribute("followError", "You cannot follow yourself.");
            resp.sendRedirect("/user/" + followingId);
            return;
        }

        if (followerDAO.isFollowing(followerId, followingId)) {
            req.setAttribute("following", true);
//            req.getSession().setAttribute("followError", "You are already following this user.");
            resp.sendRedirect("/user/" + followingId);
            return;
        }

        followerDAO.addFollower(follow);

        req.setAttribute("following", true);

        resp.sendRedirect("/user/" + followingId);

    }

    // Add additional methods for updating/removing followers, retrieving follower lists, etc.
}
