package com.sys.bio.back.criteria;

import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReceptionCriteria {

    private StringFilter responsible;
    private IntegerFilter acceptedBales;
    private IntegerFilter rejectedBales;
    private LocalDateFilter filterDate;

}
