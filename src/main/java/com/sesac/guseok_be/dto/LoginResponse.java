package com.sesac.guseok_be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // 작성자 : 박유현
@AllArgsConstructor
public class LoginResponse {
    private String email;
    private String token; // JWT 토큰
}