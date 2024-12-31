package com.sesac.guseok_be.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name="TRASH")
public class TrashSchedule {
    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="DISTRICT")
    private String district;

    @Column(name="DETAIL")
    private String detail;

    @Column(name="HOUSEHOLD")
    private String household;

    @Column(name="FOODGARBAGE")
    private String foodGarbage;

    @Column(name="PLASTIC")
    private String plastic;

    @Column(name="RECYCLE")
    private String recycle;

    @Column(name="EMISSIONTIME")
    private String emissionTime;

    @Column(name="HOMPAGE")
    private String homepage;

    public TrashSchedule() {}
}
