package com.semcon.sgu.modules.systems.services;

import com.semcon.sgu.modules.systems.dto.SystemRoleDto;
import com.semcon.sgu.modules.systems.entity.System;
import com.semcon.sgu.modules.systems.exceptions.SystemNotFound;
import com.semcon.sgu.modules.systems.mappers.SystemRoleMapper;
import com.semcon.sgu.modules.systems.repository.SystemRepository;
import com.semcon.sgu.modules.systems.repository.SystemRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemRoleService {

    private final SystemRoleRepository systemRoleRepository;
    private final SystemRepository systemRepository;
    private final SystemRoleMapper systemRoleMapper;

    public List<SystemRoleDto> getSystemRoles(Integer systemId) {
        System system = systemRepository.findById(systemId).orElse(null);
        if (system == null) {
            throw new SystemNotFound();
        }
        return systemRoleMapper.toDtoList(systemRoleRepository.findAllBySystem(system));
    }
}
