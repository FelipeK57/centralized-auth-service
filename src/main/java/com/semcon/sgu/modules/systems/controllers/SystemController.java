package com.semcon.sgu.modules.systems.controllers;

import com.semcon.sgu.modules.systems.dto.CreateSystemDto;
import com.semcon.sgu.modules.systems.dto.SystemDto;
import com.semcon.sgu.modules.systems.services.SystemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/systems")
@RequiredArgsConstructor
@Tag(name = "Systems", description = "Endpoints for managing systems and retrieving system information.")
public class SystemController {

    private final SystemService systemService;

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<SystemDto>> getSystems() {
        return ResponseEntity.status(HttpStatus.OK).body(systemService.getSystems());
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<SystemDto> createSystem(@RequestBody CreateSystemDto createSystemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(systemService.createSystem(createSystemDto));
    }
}
