package com.preschool.preschoolhome.common.exception;


import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();
    HttpStatus getHttpStatus();
    String getMessage();
}
