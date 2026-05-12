package com.semcon.sgu.modules.workareas.controller;

import com.semcon.sgu.modules.workareas.dtos.CreateWorkAreaDto;
import com.semcon.sgu.modules.workareas.dtos.WorkAreaDto;
import com.semcon.sgu.modules.workareas.services.WorkAreaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/work-areas")
public class WorkAreaController {

    private final WorkAreaService workAreaService;

    @GetMapping
    @Tag(name = "Work Areas", description = "Endpoints for managing work areas")
    public ResponseEntity<List<WorkAreaDto>> getAllWorkAreas() {
        return ResponseEntity.status(HttpStatus.OK).body(this.workAreaService.getWorkAreasList());
    }

    @PostMapping
    @Tag(name = "Get or Create Work Area", description = "Endpoints for get or create a new work area")
    public ResponseEntity<WorkAreaDto> createWorkArea(@Validated @RequestBody CreateWorkAreaDto createWorkAreaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.workAreaService.getOrCreateWorkAreaByName(createWorkAreaDto));
    }
}
