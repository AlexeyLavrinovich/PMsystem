package com.PMsystem.repository;

import com.PMsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    UserEntity findById(String id);

    UserEntity findByUsername(String username);
}
