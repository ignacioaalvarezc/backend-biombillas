package com.sys.bio.back.criteria;

import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SizingCriteria {

    private StringFilter responsible;
    private IntegerFilter totalWeight;
    private IntegerFilter totalAmount;
    private IntegerFilter totalHours;
    private LocalDateFilter filterDate;
}
