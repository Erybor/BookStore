package controller.servlet;

import dao.FollowerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveFollowerServlet", value = "/RemoveFollowerServlet")
public class RemoveFollowerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FollowerDAO followerDAO = (FollowerDAO) req.getServletContext().getAttribute("followerDAO");

        int followerId = Integer.parseInt(req.getParameter("followerId"));
        int followingId = Integer.parseInt(req.getParameter("followingId"));


        followerDAO.removeFollower(followerId, followingId);
        req.setAttribute("following", false);


        resp.sendRedirect("/user/" + followingId);

    }
}
