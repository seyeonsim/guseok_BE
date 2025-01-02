package com.sesac.guseok_be.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {

    private String name;
    private Date birth;
    private String gender;
    private String district;
}
