package com.semcon.sgu.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AppException
        extends RuntimeException {

    private final HttpStatus status;
    private final String code;

    protected AppException(
            String message,
            String code,
            HttpStatus status) {

        super(message);

        this.code = code;
        this.status = status;
    }
}