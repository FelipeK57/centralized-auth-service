package com.semcon.sgu.modules.workareas.repository;

import com.semcon.sgu.modules.workareas.entity.WorkArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkAreaRepository extends JpaRepository<WorkArea, Integer> {
    WorkArea findByName(String name);
}
