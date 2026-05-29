package com.semcon.sgu.modules.systems.repository;

import com.semcon.sgu.modules.systems.entity.System;
import com.semcon.sgu.modules.systems.entity.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Integer> {
    List<SystemRole> findAllBySystem(System system);
}
