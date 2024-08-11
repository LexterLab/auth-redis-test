package com.tinqinacademy.redis.persistence.repositories;

import com.tinqinacademy.redis.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsernameIgnoreCase(String username);

}
