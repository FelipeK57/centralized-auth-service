package com.semcon.sgu.modules.systems.controllers;

import com.semcon.sgu.modules.systems.dto.SystemRoleDto;
import com.semcon.sgu.modules.systems.services.SystemRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/systems/roles")
@RequiredArgsConstructor
@Tag(name = "System Roles", description = "Endpoints for managing system roles and retrieving system role information.")
public class SystemRolesController {

    private final SystemRoleService systemRoleService;

    @GetMapping("/{systemId}")
    @PreAuthorize("hasRole('admin')")
    public List<SystemRoleDto> getSystemRoles(@PathVariable Integer systemId) {
        return systemRoleService.getSystemRoles(systemId);
    }
}
