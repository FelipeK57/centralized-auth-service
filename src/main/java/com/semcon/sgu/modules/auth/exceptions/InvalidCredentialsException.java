package com.semcon.sgu.modules.auth.exceptions;

import com.semcon.sgu.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends AppException {
    public InvalidCredentialsException() {
        super("Credenciales inválidas", "INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
    }
}
