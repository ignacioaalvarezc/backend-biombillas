package com.sys.bio.back.models.sized;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "maintenances")
public class Maintenance {

    @Id
    private Long maintenanceId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAlert;
    private LocalTime hourAlert;
    private String reason;
    private String state;
    private String operator;
}
