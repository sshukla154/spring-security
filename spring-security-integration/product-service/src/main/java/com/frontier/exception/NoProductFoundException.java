package com.frontier.exception;

/*
 * RuntimeException implementation in case of invalid coupon
 */
public class NoProductFoundException extends RuntimeException {

    private static final long serialVersionUID = -2695049364698092364L;

    public NoProductFoundException() {
        super();
    }

    public NoProductFoundException(String message) {
        super(message);
    }

    public NoProductFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
