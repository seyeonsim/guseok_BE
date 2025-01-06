package com.sesac.guseok_be.domain;

//작성자: 윤정연;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmokingDTO {
    private List<String> districts;
    private List<Smoking> smokingAreas;
    private List<NoSmoking> noSmokingAreas;
}
