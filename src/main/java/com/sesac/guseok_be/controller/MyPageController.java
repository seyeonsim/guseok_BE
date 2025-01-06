package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.config.JwtProvider;
import com.sesac.guseok_be.domain.Smoking;
import com.sesac.guseok_be.domain.User;
import com.sesac.guseok_be.entity.ParkEntity;
import com.sesac.guseok_be.repository.UserRepository;
import com.sesac.guseok_be.service.ParkLikeService;
import com.sesac.guseok_be.service.SmokingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//작성자: 박유현;
@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final JwtProvider jwtProvider;
    private final ParkLikeService parkLikeService;
    private final SmokingService smokingService;
    private final UserRepository userRepository;

    @GetMapping("/likedParks")
    public ResponseEntity<List<ParkEntity>> getLikedParks(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        log.info("@2token: " + token);
        if (token == null) {
            return ResponseEntity.status(401).build(); // 유효하지 않은 토큰
        }

//        token = token.substring(7); // Bearer 앞부분 제거
        String email = jwtProvider.getUsernameFromToken(token); // JWT에서 이메일 추출

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<ParkEntity> likedParks = parkLikeService.getLikedParks(user.getEmail());
        return ResponseEntity.ok(likedParks);
    }

    //작성자: 윤정연;

    @GetMapping("/likedSmoking")
    public ResponseEntity<List<Smoking>> getLikedSmoking(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return ResponseEntity.status(401).build();
        }

        String email = jwtProvider.getUsernameFromToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<Smoking> likedSmokingAreas = smokingService.getLikedSmokingAreas(user.getEmail());
        return ResponseEntity.ok(likedSmokingAreas);
    }
}
