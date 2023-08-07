package controller.validator;

import dao.UserDAO;
import model.error.ValidationError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniquenessValidator implements Validator {
    //    private final long id;
    private final String username;
    UserDAO userDAO;


    private List<ValidationError> errors;

    public UniquenessValidator(String username, UserDAO userDAO) {

        this.username = username;
        this.userDAO = userDAO;
        this.errors = new ArrayList<>();
    }

    @Override
    public boolean validate() {
        if (userDAO.getUser(username) != null) {
            errors.add(new ValidationError(ValidationField.USERNAME,"The username is already taken"));
        }
        return errors.isEmpty();

    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
