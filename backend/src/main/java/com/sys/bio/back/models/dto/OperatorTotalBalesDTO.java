package com.sys.bio.back.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OperatorTotalBalesDTO {
    private String operatorName;
    private Long acceptedBales;
    private Long rejectedBales;
}
