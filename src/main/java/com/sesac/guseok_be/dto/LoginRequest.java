package com.sesac.guseok_be.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}