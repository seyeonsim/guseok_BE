package com.sesac.guseok_be.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="NAME")
    private String name;

    @Column(name="BIRTH")
    private Date birth;

    @Column(name="GENDER")
    private String gender;

    @Column(name="DISTRICT")
    private String district;

    public User() {}

}
