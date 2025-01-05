package com.sesac.guseok_be.service;

import com.sesac.guseok_be.domain.User;
import com.sesac.guseok_be.entity.LikeEntity;
import com.sesac.guseok_be.entity.ParkEntity;
import com.sesac.guseok_be.repository.LikeRepository;
import com.sesac.guseok_be.repository.ParkRepository;
import com.sesac.guseok_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ParkLikeService {

    private final UserRepository userRepository;
    private final ParkRepository parkRepository;
    private final LikeRepository likeRepository;

    /**
     * 사용자가 좋아요한 공원 목록을 반환
     */
    @Transactional(readOnly = true)
    public List<ParkEntity> getLikedParks(String userEmail) {
        // 사용자 조회
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // `likes` 테이블에서 사용자가 좋아요한 공원 ID 목록 조회
        List<Long> likedParkIds = likeRepository.findLikedTargetIdsByUserIdAndType(user.getId(), "park");

        // 공원 ID를 기반으로 공원 엔티티 목록 조회
        return parkRepository.findAllById(likedParkIds);
    }

    /**
     * 좋아요 토글 (추가/취소)
     */
    @Transactional
    public String toggleLike(String userEmail, Long parkId) {
        // 사용자 조회
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 공원 조회
        ParkEntity park = parkRepository.findById(parkId)
                .orElseThrow(() -> new IllegalArgumentException("공원을 찾을 수 없습니다."));

        // `likes` 테이블에서 기존 좋아요 데이터 확인
        LikeEntity existingLike = likeRepository.findByUserIdAndTargetTypeAndTargetId(
                user.getId(), "park", parkId
        ).orElse(null);

        if (existingLike != null) {
            // 이미 좋아요한 경우 -> 좋아요 취소
            likeRepository.delete(existingLike);
            return "좋아요 취소됨";
        }

        // 좋아요 추가
        LikeEntity newLike = LikeEntity.builder()
                .user(user)
                .targetType("park")
                .targetId(parkId)
                .createAt(LocalDateTime.now())
                .build();

        likeRepository.save(newLike);
        return "좋아요 완료됨";
    }
}
