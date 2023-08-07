package controller.servlet;

import controller.validator.LoginValidator;
import dao.UserDAO;
import model.User;
import model.error.ValidationError;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDAO");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginValidator loginValidator = new LoginValidator(username, password, userDAO);

        if (!loginValidator.validate()) {
            List<ValidationError> errors = loginValidator.getErrors();
            for (var e : errors) {
                System.out.println(e.getErrorMessage());
            }
            req.setAttribute("ErrorMessage", errors.get(0).getErrorMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        User user = userDAO.getUser(username);


        req.getSession().setAttribute("userId", user.getId());
        req.getSession().setAttribute("username", user.getUsername());
        req.getSession().setAttribute("isUserLoggedIn",true);


        // TODO Add more info attributes in session later on.

        resp.sendRedirect("/homepage");

    }
}
