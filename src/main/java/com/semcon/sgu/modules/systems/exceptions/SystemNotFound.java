package com.semcon.sgu.modules.systems.exceptions;

import com.semcon.sgu.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class SystemNotFound extends AppException {
    public SystemNotFound() {
        super("Sistema no encontrado", "SYSTEM_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
