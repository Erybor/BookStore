package controller.validator;

import dao.UserDAO;
import model.error.ValidationError;
import util.Pair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterValidator implements Validator {

    private final String email;
    private final String username;
    private final String password;
    private final String passwordRepeat;
    private final UserDAO userDAO;

    private List<ValidationError> errors;

    public RegisterValidator(String email, String username, String password, String passwordRepeat, UserDAO userDAO) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
        this.userDAO = userDAO;
        this.errors = new ArrayList<>();

    }


    // TODO NEEDS TESTING
    private boolean isEntirelyFilled() {
        List<Pair<ValidationField, String>> pairs = Arrays.asList(
                new Pair<>(ValidationField.EMAIL, email),
                new Pair<>(ValidationField.USERNAME, username),
                new Pair<>(ValidationField.PASSWORD, password)
        );

        for (Pair pair : pairs) {
            ValidationField key = (ValidationField) pair.getFirst();
            Object value = pair.getSecond();

            if (value == null) {
                errors.add(new ValidationError(key, " has to be be inputed"));
            }
        }
        return errors.isEmpty();
    }

    private boolean isUniqueUserName() throws SQLException {
        UniquenessValidator uniquenessValidator = new UniquenessValidator(username, userDAO);
        if (!uniquenessValidator.validate()) {
            errors.addAll(uniquenessValidator.getErrors());
        }
        return errors.isEmpty();
    }

    private boolean isCorrectFormatForPassword() throws SQLException {
        PasswordValidator passwordValidator = new PasswordValidator(this.password);

        if (!passwordValidator.validate()) {
            System.out.println("INCORRECT FORMAT");
            errors.addAll(passwordValidator.getErrors());
        }

        System.out.println("CORRECT " + "FORMAT");
        return errors.isEmpty();
    } // TODO SHOULD WE ADD SECOND INPUT OF A PASSWORD "CONFIRM PASSWORD"


    private boolean doPasswordsMatch(String password, String passwordRepeat) {
        PasswordsMatchValidator passwordsMatchValidator = new PasswordsMatchValidator(password, passwordRepeat);
        if (!passwordsMatchValidator.validate()) {
            errors.addAll(passwordsMatchValidator.getErrors());
        }
        return errors.isEmpty();
    }

    @Override
    public boolean validate() {
        try {
            return isEntirelyFilled() &&
                    isUniqueUserName() &&
                    isCorrectFormatForPassword() &&
                    doPasswordsMatch(password, passwordRepeat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // TODO SHOULD WE CHECK ALL THE CASES OR RETURN ON THE FIRST FALSE

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}