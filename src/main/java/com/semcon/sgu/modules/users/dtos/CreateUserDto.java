package com.semcon.sgu.modules.users.dtos;

import com.semcon.sgu.modules.users.entity.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @NotBlank
        String documentType,
        @NotBlank
        String documentNumber,
        @NotNull
        Roles role,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String workArea
) {
}
