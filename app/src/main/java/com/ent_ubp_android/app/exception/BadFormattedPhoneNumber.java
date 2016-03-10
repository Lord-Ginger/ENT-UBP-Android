package com.ent_ubp_android.app.exception;

public class BadFormattedPhoneNumber extends RuntimeException {
    private static final long serialVersionUID = -3765913060727719390L;

    public BadFormattedPhoneNumber() {
    }

    public BadFormattedPhoneNumber(String message) {
        super(message);
    }

    public BadFormattedPhoneNumber(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFormattedPhoneNumber(Throwable cause) {
        super(cause);
    }
}
