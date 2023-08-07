package controller.validator;

import dao.UserDAO;
import model.User;
import model.error.ValidationError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsernameExistsValidator implements Validator {

    private final String userName;
    private final UserDAO userDAO;
    private final List<ValidationError> errors;

    public UsernameExistsValidator(String userName, UserDAO userDAO) {
        this.userName = userName;
        this.userDAO = userDAO;
        this.errors = new ArrayList<>();
    }

    @Override
    public boolean validate() {
        User user = userDAO.getUser(userName);
        if (user == null) {
            errors.add(new ValidationError(ValidationField.USERNAME, "Username does not exist"));
            return false;
        }
        return true;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}

