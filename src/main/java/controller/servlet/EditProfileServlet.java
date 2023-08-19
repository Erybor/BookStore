package controller.servlet;

import dao.BookDAO;
import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProfileServlet", value = "/EditProfile")
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EditProfileServlet: doPost");
        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDAO");

        int userId = (int) req.getSession().getAttribute("userId");

        String name = req.getParameter("name");
        System.out.println("NAME:" + name);
//        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String lastName = req.getParameter("lastName");
        System.out.println("LAST NAME:" + lastName);

        String url = req.getParameter("url");

        userDAO.updateUserProfile(userId, name, lastName, gender, url);

//        User user = userDAO.getUserByID(userId);

//        req.setAttribute("user", user);

        // Need to send the Get request

        resp.sendRedirect("/profile");

//        req.getRequestDispatcher("/profile").forward(req, resp);

    }
}
