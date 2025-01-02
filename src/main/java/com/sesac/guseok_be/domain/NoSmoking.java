package com.sesac.guseok_be.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="NOSMOKING")
public class NoSmoking {
    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="AREA", columnDefinition = "VARCHAR2(500 CHAR)")
    private String area;

    @Column(name="SCOPE", columnDefinition = "VARCHAR2(500 CHAR)")
    private String scope;

    @Column(name="DISTRICT", columnDefinition = "VARCHAR2(500 CHAR)")
    private String district;

    @Column(name="STATUTE", columnDefinition = "VARCHAR2(1000 CHAR)")
    private String statute;

    @Column(name="FINE")
    private Long fine;

    @Column(name="NOTIFYNUMBER", columnDefinition = "VARCHAR2(1000 CHAR)")
    private String notifyNumber;

    @Column(name="ADDRESS", columnDefinition = "VARCHAR2(1000 CHAR)")
    private String address;

    @Column(name="LATITUDE")
    private Double latitude;

    @Column(name="LONGITUDE")
    private Double longitude;

    public NoSmoking() {}
}
