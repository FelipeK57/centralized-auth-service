package com.semcon.sgu.modules.users.controller;

import com.semcon.sgu.modules.users.dtos.CreateUserDto;
import com.semcon.sgu.modules.users.dtos.UserDto;
import com.semcon.sgu.modules.users.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for managing users")
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<UserDto>> getUsersList() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersList());
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDto));
    }
}
