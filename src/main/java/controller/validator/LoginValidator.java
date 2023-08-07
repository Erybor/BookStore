package controller.validator;

import dao.UserDAO;
import model.error.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class LoginValidator implements Validator {

    private String userName;
    private String password;
    private List<ValidationError> errors;
    private UsernameExistsValidator usernameExistsValidator;
    private LoginPasswordValidator loginPasswordValidator;


    public LoginValidator(String userName, String password, UserDAO userDAO) {
        this.userName = userName;
        this.password = password;
        this.loginPasswordValidator = new LoginPasswordValidator(userName, password, userDAO);
        this.usernameExistsValidator = new UsernameExistsValidator(userName, userDAO);
        this.errors = new ArrayList<>();

    }


    @Override
    public boolean validate() {

        if (userName == null || userName.isEmpty()) { // TODO SHOULD WE PRINT ALL MISSING INFO, OR JUST THE FIRST ONE?
            errors.add(new ValidationError(ValidationField.USERNAME, "Username Field has to be not empty"));
        } else if (password == null || password.isEmpty()) {
            errors.add(new ValidationError(ValidationField.PASSWORD, "Password Field has to be not empty"));
        } else if (!usernameExistsValidator.validate()) {
            errors.addAll(usernameExistsValidator.getErrors());
        } else if (!loginPasswordValidator.validate()) {
            errors.addAll(loginPasswordValidator.getErrors());
        }

        return errors.isEmpty();
    }


    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
