package com.sys.bio.back.models.reception;

import com.sys.bio.back.models.user.Responsible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "receptions")
public class Reception {

    @Id
    private Long receptionId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Responsible responsible;
    private Integer acceptedBales;
    private Integer rejectedBales;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private LocalTime hour;
    private String reasonRejected;
    private Double rejectedPercentage;
    private LocalDate filterDate;

}
