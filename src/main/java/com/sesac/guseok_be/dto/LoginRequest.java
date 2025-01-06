package com.sesac.guseok_be.dto;

import lombok.Getter;

@Getter // 작성자 : 박유현
public class LoginRequest {
    private String email;
    private String password;
}