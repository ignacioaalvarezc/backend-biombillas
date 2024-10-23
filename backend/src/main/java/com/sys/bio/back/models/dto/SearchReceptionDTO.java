package com.sys.bio.back.models.dto;

import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class SearchReceptionDTO {

    private String responsible;
    private Integer startAcceptedBales;
    private Integer endAcceptedBales;
    private Integer startRejectedBales;
    private Integer endRejectedBales;
    private LocalDate startDate;
    private LocalDate endDate;
}
