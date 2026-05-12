package com.semcon.sgu.modules.users.dtos;

import com.semcon.sgu.modules.workareas.dtos.WorkAreaDto;

public record UserDto(
        Integer id,
        String name,
        String lastName,
        String email,
        WorkAreaDto workArea
) {
}
