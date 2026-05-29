package com.semcon.sgu.modules.systems.controllers;

import com.semcon.sgu.modules.auth.dtos.LoginRequestDto;
import com.semcon.sgu.modules.auth.dtos.LoginResponseDto;
import com.semcon.sgu.modules.systems.services.SystemAccessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/system-access")
@RequiredArgsConstructor
@Tag(name = "System Access", description = "Endpoints for system authentication and access control.")
public class SystemAccessController {

    private final SystemAccessService systemAccessService;

    @PostMapping
    @Operation(summary = "System Authentication", description = "Authenticate a user for system access using email, password, and API key.")
    public ResponseEntity<LoginResponseDto> systemAuthentication(@RequestBody LoginRequestDto loginRequestDto, @RequestHeader(name = "x-api-key") String key) {
        return ResponseEntity.ok(systemAccessService.systemAuthentication(loginRequestDto, key));
    }
}
