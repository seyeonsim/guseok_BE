package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.entity.LikeEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 작성자 : 심세연

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    @Query("SELECT l.targetId FROM LikeEntity l WHERE l.user.id = :userId AND l.targetType = :type")
    List<Long> findLikedTargetIdsByUserIdAndType(@Param("userId") Long userId, @Param("type") String type);

    // 작성자 : 심세연
    Optional<LikeEntity> findByUserIdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
}
