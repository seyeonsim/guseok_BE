package com.sesac.guseok_be.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {

    private String name;
    private LocalDate birth;
    private String gender;
    private String district;
}
