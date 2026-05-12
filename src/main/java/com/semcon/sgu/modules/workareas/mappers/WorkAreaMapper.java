package com.semcon.sgu.modules.workareas.mappers;

import com.semcon.sgu.modules.workareas.dtos.WorkAreaDto;
import com.semcon.sgu.modules.workareas.entity.WorkArea;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkAreaMapper {
    WorkAreaDto toDto(WorkArea workArea);

    List<WorkAreaDto> toDtoList(List<WorkArea> workAreas);
}
