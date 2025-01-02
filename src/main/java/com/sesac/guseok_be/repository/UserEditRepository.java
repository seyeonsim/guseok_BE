package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserEditRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u WHERE (:email IS NULL OR u.email = :email)")
//    User findByEmail(String email);

    User findByEmail(String email);

}
