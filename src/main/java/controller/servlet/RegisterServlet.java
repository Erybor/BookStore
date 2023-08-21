package controller.servlet;

import controller.validator.LoginPasswordValidator;
import controller.validator.PasswordsMatchValidator;
import controller.validator.RegisterValidator;
import dao.UserDAO;
import model.User;
import model.error.ValidationError;
import util.Hasher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HAHAHA");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("passwordRepeat");
        String email = req.getParameter("email");


//        System.out.printf("%s %s %s", username, password, email);
        UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDAO");

        List<ValidationError> errors = new ArrayList<>();
        RegisterValidator regVal = new RegisterValidator(email, username, password, passwordRepeat, userDAO);

        // I'm saving all the errors for testing, but only passing the first one to the JSP.

        if (!regVal.validate()) {
            errors = regVal.getErrors();
            for (ValidationError e : errors) {
                System.out.println(e.getErrorMessage());
            }
            req.setAttribute("ErrorMessage", errors.get(0).getErrorMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        for (ValidationError s : errors) {
            System.out.println(s.getErrorMessage());
        }


        User user = new User(username, password, email);

//        userDAO.addUser(username, password, email)
//        userDAO.addUser(user);
        userDAO.addUser(user);

        req.getSession().setAttribute("userId", user.getId());
        req.getSession().setAttribute("username", user.getUsername());


        resp.sendRedirect("/homepage");

    }
}