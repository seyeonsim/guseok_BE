package com.sesac.guseok_be.entity;

import com.sesac.guseok_be.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // 작성자 : 박유현
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
    @ManyToMany(mappedBy = "likedParks")
    private List<User> likedUsers = new ArrayList<>();
}
