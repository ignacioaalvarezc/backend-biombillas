package com.sys.bio.back.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class SearchCuttingDTO {

    private String responsible;

    private Integer startTotalWeight;
    private Integer endTotalWeight;

    private Integer startTotalAmount;
    private Integer endTotalAmount;

    private Integer startTotalHours;
    private Integer endTotalHours;

    private LocalDate startDate;
    private LocalDate endDate;
}