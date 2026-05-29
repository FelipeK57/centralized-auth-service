package com.semcon.sgu.modules.systems.repository;

import com.semcon.sgu.modules.systems.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {
}
