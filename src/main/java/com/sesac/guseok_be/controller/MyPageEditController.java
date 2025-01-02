package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.config.JwtProvider;
import com.sesac.guseok_be.domain.User;
import com.sesac.guseok_be.domain.UserDTO;
import com.sesac.guseok_be.service.UserEditService;
import jakarta.persistence.Access;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/mypage/")
public class MyPageEditController {

    @Autowired
    private JwtProvider jwtProvider;

    private final UserEditService userEditService;

    public MyPageEditController(UserEditService userEditService) {
        this.userEditService = userEditService;
    }

    @GetMapping("/")
    public ResponseEntity<User> getMyPage(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        String useremail = jwtProvider.getUsernameFromToken(token);
        System.out.println("Authenticated user email: " + useremail); // 디버깅 로그

        User user = userEditService.getUser(useremail);
        if (user == null) {
            System.out.println("No user found for email: " + useremail); // 디버깅 로그
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        System.out.println("Fetched user: " + user); // 디버깅 로그
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String useremail = jwtProvider.getUsernameFromToken(token);

        User updatedUser = userEditService.updateUser(user, useremail);

        return ResponseEntity.ok(updatedUser);
    }
}
