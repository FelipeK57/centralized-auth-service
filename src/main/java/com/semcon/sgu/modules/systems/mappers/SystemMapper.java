package com.semcon.sgu.modules.systems.mappers;

import com.semcon.sgu.modules.systems.dto.SystemDto;
import com.semcon.sgu.modules.systems.entity.System;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SystemMapper {
    SystemDto toDto(System system);
    List<SystemDto> toDtoList(List<System> systems);
}