package com.sesac.guseok_be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PARK")
@Getter
@Setter
public class ParkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String district;
    private String address;
    @Column(length = 4000)
    private String information;
    private String image;
    private String map;
    private String area;
    @Column(length = 2000)
    private String facility;
    private String phone;
    private Double longitude;
    private Double latitude;
    private String link;
}
