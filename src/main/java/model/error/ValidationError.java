package model.error;

import controller.validator.ValidationField;

public class ValidationError {
    private final ValidationField field;
    private final String errorMessage;

    public ValidationError(ValidationField field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public ValidationField getField() {
        return field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}