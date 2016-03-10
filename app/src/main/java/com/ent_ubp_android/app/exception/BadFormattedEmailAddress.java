package com.ent_ubp_android.app.exception;

public class BadFormattedEmailAddress extends RuntimeException {
    private static final long serialVersionUID = -2869530471866393157L;

    public BadFormattedEmailAddress() {
    }

    public BadFormattedEmailAddress(String message) {
        super(message);
    }

    public BadFormattedEmailAddress(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFormattedEmailAddress(Throwable cause) {
        super(cause);
    }

}
