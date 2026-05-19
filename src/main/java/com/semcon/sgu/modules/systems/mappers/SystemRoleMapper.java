package com.semcon.sgu.modules.systems.mappers;

import com.semcon.sgu.modules.systems.dto.SystemRoleDto;
import com.semcon.sgu.modules.systems.entity.SystemRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SystemRoleMapper {
    SystemRoleDto toDto(SystemRole systemRole);
    List<SystemRoleDto> toDtoList(List<SystemRole> systemRoles);
}
