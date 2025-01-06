package com.sesac.guseok_be.domain;

//작성자: 윤정연;

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
