package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEditRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
