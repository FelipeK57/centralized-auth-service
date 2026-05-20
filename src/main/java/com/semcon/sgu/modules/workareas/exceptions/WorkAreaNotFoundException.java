package com.semcon.sgu.modules.workareas.exceptions;

import com.semcon.sgu.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class WorkAreaNotFoundException extends AppException {
    public WorkAreaNotFoundException() {
        super("Area de trabajo no encontrada", "WORK_AREA_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
