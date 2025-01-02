package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.config.JwtProvider;
import com.sesac.guseok_be.domain.User;
import com.sesac.guseok_be.dto.AddUserRequest;
import com.sesac.guseok_be.dto.LoginRequest;
import com.sesac.guseok_be.dto.LoginResponse;
import com.sesac.guseok_be.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AddUserRequest request) {
        try {
            if (request.getEmail() == null || request.getPassword() == null || request.getName() == null ||
                    request.getBirth() == null || request.getGender() == null || request.getDistrict() == null) {
                return ResponseEntity.badRequest().body("모든 필드를 입력해주세요.");
            }
            userService.save(request);
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // 1. 사용자를 이메일로 검색
            User user = userService.findByEmail(request.getEmail());

            // 2. 비밀번호 검증
            if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
            }

            // 3. JWT 토큰 생성
            String token = jwtProvider.createToken(user.getEmail());

            // 4. 로그인 응답 반환
            return ResponseEntity.ok(new LoginResponse(user.getEmail(), token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok("로그아웃 성공");
    }
}
