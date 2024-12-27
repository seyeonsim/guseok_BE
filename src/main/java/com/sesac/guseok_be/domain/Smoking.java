package com.sesac.guseok_be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SMOKING")
public class Smoking {
    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="DISTRICT")
    private String district;

    @Column(name="ADDRESS", columnDefinition = "VARCHAR2(1000 CHAR)")
    private String address;

    @Column(name="OPEN")
    private String open;

    @Column(name="DETAIL", columnDefinition = "VARCHAR2(1000 CHAR)")
    private String detail;

    public Smoking() {
    }
}
