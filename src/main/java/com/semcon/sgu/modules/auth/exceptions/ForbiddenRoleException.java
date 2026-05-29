package com.semcon.sgu.modules.auth.exceptions;

import com.semcon.sgu.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class ForbiddenRoleException extends AppException {
    public ForbiddenRoleException() {
        super("No tienes permisos para acceder", "FORBIDDEN_ROLE", HttpStatus.FORBIDDEN);
    }
}
