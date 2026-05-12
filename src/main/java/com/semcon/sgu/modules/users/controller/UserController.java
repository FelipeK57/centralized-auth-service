package com.semcon.sgu.modules.users.controller;

import com.semcon.sgu.modules.users.dtos.CreateUserDto;
import com.semcon.sgu.modules.users.dtos.UserDto;
import com.semcon.sgu.modules.users.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Tag(name = "Get Users List", description = "Endpoint to retrieve the list of users")
    public ResponseEntity<List<UserDto>> getUsersList() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUsersList());
    }

    @PostMapping
    @Tag(name = "Create User", description = "Endpoint to create a new user")
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDto));
    }
}
