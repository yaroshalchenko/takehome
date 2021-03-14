package com.challenge.demo.repository;

import com.challenge.demo.domain.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserUUID(UUID uuid);

    List<User> findAllByUserUUID(UUID userUUID);
}
