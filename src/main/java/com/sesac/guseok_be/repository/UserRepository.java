package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // 작성자 : 박유현
    Optional<User> findByEmail(String email);
}
