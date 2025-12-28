package com.jsp.emp_api.exception;

public class DataExistEx extends RuntimeException {
    public DataExistEx(String message) {
        super(message);
    }
}
