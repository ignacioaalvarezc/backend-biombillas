package com.sys.bio.back.models.dto;

import com.sys.bio.back.models.user.Responsible;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponsibleHoursSumDTO {

    private String responsibleName;
    private Integer totalHours;

}
