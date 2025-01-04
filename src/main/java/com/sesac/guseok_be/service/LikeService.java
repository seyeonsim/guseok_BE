package com.sesac.guseok_be.service;

import com.sesac.guseok_be.domain.User;
import com.sesac.guseok_be.entity.LikeEntity;
import com.sesac.guseok_be.repository.LikeRepository;
import com.sesac.guseok_be.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Transactional
    public String toggleLike(String email, String targetType, Long targetId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 이미 좋아요를 눌렀는지 확인
        LikeEntity existingLike = likeRepository.findByUserIdAndTargetTypeAndTargetId(
                user.getId(), targetType, targetId).orElse(null);

        if (existingLike != null) {
            // 이미 좋아요한 경우 -> 좋아요 취소
            likeRepository.delete(existingLike);
            return "좋아요 취소됨";
        }

        // 좋아요 추가
        LikeEntity like = LikeEntity.builder()
                .user(user)
                .targetType(targetType)
                .targetId(targetId)
                .createAt(LocalDateTime.now())
                .build();

        likeRepository.save(like);
        return "좋아요 왼료됨";

    }

    public boolean checkIfLiked(String email, String targetType, Long targetId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 이미 좋아요를 눌렀는지 확인
        LikeEntity existingLike = likeRepository.findByUserIdAndTargetTypeAndTargetId(
                user.getId(), targetType, targetId).orElse(null);
        return existingLike != null;
    }
}
