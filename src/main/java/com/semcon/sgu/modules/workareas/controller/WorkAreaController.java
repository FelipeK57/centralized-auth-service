package com.semcon.sgu.modules.workareas.controller;

import com.semcon.sgu.modules.workareas.dtos.CreateWorkAreaDto;
import com.semcon.sgu.modules.workareas.dtos.UpdateWorkAreaDto;
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
@Tag(name = "Work Areas", description = "Endpoints for managing work areas")
@RequestMapping("/v1/work-areas")
public class WorkAreaController {

    private final WorkAreaService workAreaService;

    @GetMapping
    public ResponseEntity<List<WorkAreaDto>> getAllWorkAreas() {
        return ResponseEntity.status(HttpStatus.OK).body(this.workAreaService.getWorkAreasList());
    }

    @PostMapping
    public ResponseEntity<WorkAreaDto> createWorkArea(@Validated @RequestBody CreateWorkAreaDto createWorkAreaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.workAreaService.getOrCreateWorkAreaByName(createWorkAreaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkAreaDto> updateWorkArea(@Validated @RequestBody UpdateWorkAreaDto updateWorkAreaDto, @PathVariable Integer id) {
        return  ResponseEntity.status(HttpStatus.OK).body(this.workAreaService.updateWorkArea(id, updateWorkAreaDto));
    }
}
