package controller.validator;

import dao.UserDAO;
import model.User;
import model.error.ValidationError;
import util.Hasher;

import java.util.ArrayList;
import java.util.List;

public class PasswordsMatchValidator implements Validator {
    private final String password;
    private final String passwordRepeat;
    private List<ValidationError> errors;

    public PasswordsMatchValidator(String password, String passwordRepeat) {
        this.password = password;
        this.passwordRepeat = passwordRepeat;
        this.errors = new ArrayList<>();

    }

    @Override
    public boolean validate() {

        String passwordHashed = Hasher.generateHash(password);
        String passwordRepeatHashed = Hasher.generateHash(passwordRepeat);
        if (passwordHashed.equals(passwordRepeatHashed)) {
            return true;
        }
        errors.add(new ValidationError(ValidationField.PASSWORD,"The passwords do not match!"));
        return false;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
