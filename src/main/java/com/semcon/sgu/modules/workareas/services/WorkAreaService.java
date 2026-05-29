package com.semcon.sgu.modules.workareas.services;

import com.semcon.sgu.modules.workareas.dtos.CreateWorkAreaDto;
import com.semcon.sgu.modules.workareas.dtos.UpdateWorkAreaDto;
import com.semcon.sgu.modules.workareas.dtos.WorkAreaDto;
import com.semcon.sgu.modules.workareas.entity.WorkArea;
import com.semcon.sgu.modules.workareas.exceptions.WorkAreaNotFoundException;
import com.semcon.sgu.modules.workareas.mappers.WorkAreaMapper;
import com.semcon.sgu.modules.workareas.repository.WorkAreaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkAreaService {

    private final WorkAreaRepository workAreaRepository;
    private final WorkAreaMapper workAreaMapper;
    
    public List<WorkAreaDto> getWorkAreasList() {
        List<WorkArea> workAreas = this.workAreaRepository.findAll();
        return workAreaMapper.toDtoList(workAreas);
    }

    public WorkAreaDto getOrCreateWorkAreaByName(CreateWorkAreaDto createWorkAreaDto) {
        WorkArea existingWorkArea = workAreaRepository.findByName(createWorkAreaDto.name());
        if (existingWorkArea == null) {
            WorkArea workArea = WorkArea.builder()
                    .name(createWorkAreaDto.name())
                    .build();
            workAreaRepository.save(workArea);
            return workAreaMapper.toDto(workArea);
        } else {
            return workAreaMapper.toDto(existingWorkArea);
        }
    }

    public WorkAreaDto updateWorkArea(Integer id, UpdateWorkAreaDto updateWorkAreaDto) {
        WorkArea existingWorkArea = workAreaRepository.findById(id).orElse(null);
        if (existingWorkArea == null) {
            throw new WorkAreaNotFoundException();
        }
        existingWorkArea.setName(updateWorkAreaDto.name());
        workAreaRepository.save(existingWorkArea);
        return workAreaMapper.toDto(existingWorkArea);
    }
}
