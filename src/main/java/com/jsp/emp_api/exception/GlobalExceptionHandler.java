package com.jsp.emp_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(DataNotFoundEx ex) {
        return Map.of(
            "timestamp", LocalDateTime.now(),
            "status", 404,
            "message", ex.getMessage()
        );
    }

    @ExceptionHandler(DataExistEx.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleExist(DataExistEx ex) {
        return Map.of(
            "timestamp", LocalDateTime.now(),
            "status", 409,
            "message", ex.getMessage()
        );
    }
}
