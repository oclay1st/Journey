package com.smart.life.kernel;

import lombok.Getter;

@Getter
public class JourneyException extends RuntimeException {

    enum ErrorType {
        RESOURCE_NOT_FOUND, UNEXPECTED, UNAUTHORIZED, PRECONDITION_FAILED, UNSUPPORTED_TYPE, IVALID_DATA
    }

    private final ErrorType errorType;

    private JourneyException(String message, Throwable throwable) {
        super(message, throwable);
        errorType = ErrorType.UNEXPECTED;
    }

    private JourneyException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public static JourneyException notFound(String message) {
        return new JourneyException(message, ErrorType.RESOURCE_NOT_FOUND);
    }

    public static JourneyException invalidData(String message) {
        return new JourneyException(message, ErrorType.IVALID_DATA);
    }

    public static JourneyException unauthorized(String message) {
        return new JourneyException(message, ErrorType.UNAUTHORIZED);
    }

    public static JourneyException preconditionFailed(String message) {
        return new JourneyException(message, ErrorType.PRECONDITION_FAILED);
    }

    public static JourneyException unsupportedType(String message) {
        return new JourneyException(message, ErrorType.UNSUPPORTED_TYPE);
    }

    public static JourneyException unexpected(String message, Throwable throwable) {
        return new JourneyException(message, throwable);
    }

}
