package com.semcon.sgu.modules.users.repository;

import com.semcon.sgu.modules.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
