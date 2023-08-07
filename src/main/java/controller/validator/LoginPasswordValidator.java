package controller.validator;

import dao.UserDAO;
import model.User;
import model.error.ValidationError;
import util.Hasher;

import java.util.ArrayList;
import java.util.List;

public class LoginPasswordValidator implements Validator {
    private final String userName;
    private final String password;
    private final UserDAO userDAO;
    private List<ValidationError> errors;

    public LoginPasswordValidator(String userName, String password, UserDAO userDAO) {
        this.userName = userName;
        this.password = password;
        this.userDAO = userDAO;
        this.errors = new ArrayList<>();
    }

    @Override
    public boolean validate() {
        User user = null;
        user = userDAO.getUser(userName);
        String passwordHashed = Hasher.generateHash(password);
        String userPasswordHashed = Hasher.generateHash(user.getPassword());
        if (userPasswordHashed.equals(passwordHashed)) {
            return true;
        }
        errors.add(new ValidationError(ValidationField.PASSWORD,"The password is incorrect"));
        return false;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
