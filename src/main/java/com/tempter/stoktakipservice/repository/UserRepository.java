package com.tempter.stoktakipservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tempter.stoktakipservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
