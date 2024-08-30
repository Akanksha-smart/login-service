package com.sam.crickstatloginservice.repository;

import com.sam.crickstatloginservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
