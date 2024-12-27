package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.entity.CulturalEventEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CulturalEventRepository extends JpaRepository<CulturalEventEntity, Integer> {

//    @Query("SELECT e FROM CulturalEventEntity e WHERE e.end_date > CURRENT_DATE")
//    List<CulturalEventEntity> findEventsAfterToday();

    List<CulturalEventEntity> findByEndDateAfterAndDistrict(LocalDate localDate, String district);

}
