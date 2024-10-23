package com.sys.bio.back.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NotificationDTO {

    @NotNull
    private Long userId;

    @NotNull
    private String message;
}
