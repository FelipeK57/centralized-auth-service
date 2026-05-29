package com.semcon.sgu.modules.systems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.semcon.sgu.modules.systems.entity.System;

public interface SystemRepository extends JpaRepository<System, Integer> {
    System findByKey(String key);
    Boolean existsByKey(String key);
}
