package com.semcon.sgu.modules.systems.services;

import com.semcon.sgu.modules.systems.dto.CreateSystemDto;
import com.semcon.sgu.modules.systems.dto.SystemDto;
import com.semcon.sgu.modules.systems.entity.System;
import com.semcon.sgu.modules.systems.mappers.SystemMapper;
import com.semcon.sgu.modules.systems.repository.SystemRepository;
import com.semcon.sgu.modules.systems.utils.ApiKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final SystemRepository systemRepository;
    private final SystemMapper systemMapper;
    private final ApiKeyGenerator apiKeyGenerator;

    public List<SystemDto> getSystems() {
        List<System> systems = systemRepository.findAll();
        return systemMapper.toDtoList(systems);
    }

    public SystemDto createSystem(CreateSystemDto createSystemDto) {
        String key;
        do {
            key = apiKeyGenerator.generateApiKey();
        } while (systemRepository.existsByKey(key));
        System system = System.builder()
                .name(createSystemDto.name())
                .url(createSystemDto.url())
                .key(key)
                .build();
        systemRepository.save(system);
        return systemMapper.toDto(system);
    }
}
