package com.semcon.sgu.modules.users.mappers;

import com.semcon.sgu.modules.users.dtos.UserDto;
import com.semcon.sgu.modules.users.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);
}
