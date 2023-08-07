package controller.validator;

import model.error.ValidationError;

import java.sql.SQLException;
import java.util.List;

public interface Validator {

    boolean validate();

    List<ValidationError> getErrors();
}