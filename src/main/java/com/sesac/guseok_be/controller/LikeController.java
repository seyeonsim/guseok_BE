package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.config.JwtProvider;
import com.sesac.guseok_be.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/like")
public class LikeController {
    private final LikeService likeService;
    private final JwtProvider jwtProvider;

    @PostMapping("/{targetType}/{targetId}")
    public ResponseEntity<String> toggleLike(
            @PathVariable String targetType,
            @PathVariable Long targetId,
            HttpServletRequest request) {

        // JWT 토큰에서 사용자 이메일 추출
        String token = jwtProvider.resolveToken(request);
        System.out.println("토큰: " + token);  // 토큰을 확인하는 로그 추가


        if (token == null || !jwtProvider.validateToken(token)) {
            return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
        }

        String email = jwtProvider.getUsernameFromToken(token);
        String result = likeService.toggleLike(email, targetType, targetId);
        return ResponseEntity.ok(result);
    }

}
