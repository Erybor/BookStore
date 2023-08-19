package controller.servlet;

import dao.UserPostDAO;
import model.UserPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeletePostServlet", value = "/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserPostDAO postDAO = (UserPostDAO) req.getServletContext().getAttribute("postDAO");
        int postId = Integer.parseInt(req.getParameter("postId"));


        UserPost post = postDAO.getPostById(postId);
        if (postId != post.getPostId()) {
            resp.sendError(403, "You are not authorized to delete this post");
            return;
        }

        if (post.getUserId() != (int) req.getSession().getAttribute("userId")) {
            resp.sendError(403, "You are not authorized to delete this post");
            return;
        }

        postDAO.deletePost(postId);

        List<UserPost> posts = postDAO.getFollowerPosts((int) req.getSession().getAttribute("userId"));
        req.setAttribute("posts", posts);

        resp.sendRedirect("/homepage");
    }
}
