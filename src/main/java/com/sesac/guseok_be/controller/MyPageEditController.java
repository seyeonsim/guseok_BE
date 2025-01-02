package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.domain.User;
import com.sesac.guseok_be.domain.UserDTO;
import com.sesac.guseok_be.service.UserEditService;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/mypage/")
public class MyPageEditController {

    private final UserEditService userEditService;

    public MyPageEditController(UserEditService userEditService) {
        this.userEditService = userEditService;
    }

    @GetMapping("/")
    public ResponseEntity<User> getMyPage(Principal principal) {
        if(principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String useremail = principal.getName();
        User user = userEditService.getUser(useremail);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String useremail = authentication.getName();
        User updatedUser = userEditService.updateUser(user, useremail);

        return ResponseEntity.ok(updatedUser);
    }
}
