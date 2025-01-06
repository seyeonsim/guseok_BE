package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.config.JwtProvider;
import com.sesac.guseok_be.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 작성자 : 심세연

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


    @GetMapping("/{targetType}/{targetId}")
    public ResponseEntity<Boolean> checkIfLiked(@PathVariable String targetType,
                                                @PathVariable Long targetId,
                                                HttpServletRequest request) {
        // JWT 토큰에서 이메일을 추출
        String token = jwtProvider.resolveToken(request);
        if (token == null || !jwtProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false); // 유효하지 않은 토큰
        }

        String email = jwtProvider.getUsernameFromToken(token);
        boolean isLiked = likeService.checkIfLiked(email, targetType, targetId); // 사용자 좋아요 여부 확인
        return ResponseEntity.ok(isLiked); // 좋아요 여부 반환
    }


}
