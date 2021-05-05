package com.smart.life.kernel;

import com.smart.life.kernel.JourneyException.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;;

@ControllerAdvice
public class ControllerExceptionHandler {

    private HttpStatus resolveHttpStatus(ErrorType errorType) {
        HttpStatus status;
        switch (errorType) {
            case UNAUTHORIZED:
                status = HttpStatus.UNAUTHORIZED;
                break;
            case UNSUPPORTED_TYPE:
                status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
                break;
            case RESOURCE_NOT_FOUND:
                status = HttpStatus.NOT_FOUND;
                break;
            case PRECONDITION_FAILED:
                status = HttpStatus.PRECONDITION_FAILED;
                break;
            case IVALID_DATA:
                status = HttpStatus.BAD_REQUEST;
                break;
            case UNEXPECTED:
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return status;
    }

    private Map<String, Object> resolveBody(String message, ErrorType errorType) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        body.put("errorType", errorType);
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(JourneyException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> processErrors(JourneyException exception) {
        HttpStatus status = resolveHttpStatus(exception.getErrorType());
        Map<String, Object> body = resolveBody(exception.getMessage(), exception.getErrorType());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> processValidationErrros(MethodArgumentNotValidException exception) {
        Map<String, Object> body = resolveBody("Validation Error", ErrorType.IVALID_DATA);
        Map<String, List<String>> errors = exception.getBindingResult().getAllErrors().stream()
                .map(FieldError.class::cast).collect(groupingBy(FieldError::getField,
                        Collectors.mapping(ObjectError::getDefaultMessage, Collectors.toList())));
        body.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
