package com.semcon.sgu.modules.workareas.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateWorkAreaDto(@NotBlank String name) {
}
