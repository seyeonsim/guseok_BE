package com.sesac.guseok_be.service;

//작성자: 윤정연;

import com.sesac.guseok_be.domain.Smoking;
import com.sesac.guseok_be.domain.User;
import com.sesac.guseok_be.entity.LikeEntity;
import com.sesac.guseok_be.repository.LikeRepository;
import com.sesac.guseok_be.repository.SmokingRepository;
import com.sesac.guseok_be.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SmokingService {

    private final SmokingRepository smokingRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public List<String> getDistinctDistricts() {
        return smokingRepository.findDistinctDistricts();
    }

    public List<Smoking> getDataByDistrict(String district) {
        return smokingRepository.findDataByDistrict(district);
    }

    public List<Smoking> getAllData() {
        return smokingRepository.findAllData();
    }

    @Transactional(readOnly = true)
    public List<Smoking> getLikedSmokingAreas(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<Long> likedSmokingIds = likeRepository.findLikedTargetIdsByUserIdAndType(user.getId(), "smoking");

        return smokingRepository.findAllById(likedSmokingIds);
    }

    @Transactional
    public String toggleLike(String userEmail, Long smokingId) {
        // 사용자 조회
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Smoking smoking = smokingRepository.findById(smokingId)
                .orElseThrow(() -> new IllegalArgumentException("장소를 찾을 수 없습니다."));

        LikeEntity existingLike = likeRepository.findByUserIdAndTargetTypeAndTargetId(
                user.getId(), "smoking", smokingId
        ).orElse(null);

        if (existingLike != null) {
            likeRepository.delete(existingLike);
            return "좋아요 취소";
        }

        LikeEntity newLike = LikeEntity.builder()
                .user(user)
                .targetType("smoking")
                .targetId(smokingId)
                .createAt(LocalDateTime.now())
                .build();

        likeRepository.save(newLike);

        return "좋아요 완료";
    }
}
