package com.ent_ubp_android.app.exception;

public class ModelConstraintViolationException extends RuntimeException {
    private static final long serialVersionUID = -398379325925193384L;

    public ModelConstraintViolationException() {
    }

    public ModelConstraintViolationException(String message) {
        super(message);
    }

    public ModelConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelConstraintViolationException(Throwable cause) {
        super(cause);
    }

}
