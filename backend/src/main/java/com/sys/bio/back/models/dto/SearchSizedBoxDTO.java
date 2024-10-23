package com.sys.bio.back.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class SearchSizedBoxDTO {

    private String responsible;
    private String strawType;
    private Integer startWeight;
    private Integer endWeight;
    private Integer startAmount;
    private Integer endAmount;
    private LocalDate startDate;
    private LocalDate endDate;

}
