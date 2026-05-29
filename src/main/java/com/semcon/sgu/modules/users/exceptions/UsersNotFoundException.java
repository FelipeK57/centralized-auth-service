package com.semcon.sgu.modules.users.exceptions;

import com.semcon.sgu.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class UsersNotFoundException extends AppException {
    public UsersNotFoundException() {
        super("Usuario no encontrado", "USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
