package com.sesac.guseok_be.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AddUserRequest {
    private String email;
    private String password;
    private String name;
    private LocalDate birth;
    private String gender;
    private String district;
}
