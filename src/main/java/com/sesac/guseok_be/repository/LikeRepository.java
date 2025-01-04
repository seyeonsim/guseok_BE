package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.entity.LikeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByUserIdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
}
