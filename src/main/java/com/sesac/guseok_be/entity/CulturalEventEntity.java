package com.sesac.guseok_be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "event")
public class CulturalEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    private String category;

    private String district;

    @Lob
    private String title;

    private String event_date;

    private String place;

    private String org_name;

    private String use_trgt;

    @Column(length = 1000)
    private String use_fee;

    @Column(length = 1000)
    private String org_link;

    @Column(length = 1000)
    private String main_img;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private Double lot;

    private Double lat;

    @Column(name = "is_free")
    private String isFree;

    private String hmpg_addr;

}
