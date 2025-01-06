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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController // 작성자 : 박유현
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
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

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        // 1. Request header에서 토큰 추출
        String token = jwtProvider.resolveToken(request);

        // 2. 토큰이 존재하지 않거나 유효하지 않으면 401 응답
        if (token == null || !jwtProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("로그인이 필요한 서비스입니다.");
        }

        // 3. 토큰에서 이메일 파싱
        String email;
        try {
            email = jwtProvider.getUsernameFromToken(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("토큰 파싱 중 오류가 발생했습니다.");
        }

        // 4. DB에서 유저 정보 조회 (userService 등 활용)
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("해당 유저를 찾을 수 없습니다.");
        }

        // 5. 조회한 유저 정보로 응답
        Map<String, Object> result = new HashMap<>();
        result.put("email", user.getEmail());
        result.put("name", user.getName());
        result.put("district", user.getDistrict());
        // 필요한 정보들을 더 담아주면 됩니다.

        return ResponseEntity.ok(result);
    }

}
