package com.sys.bio.back.models.sized;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sys.bio.back.models.cutting.Cutting;
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
@Table(name = "sizedBoxes")
public class SizedBox {

    @Id
    private Long sizedBoxId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Responsible responsible;
    @ManyToOne(fetch = FetchType.EAGER)
    private StrawType strawType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("sizing")
    private Sizing sizing;

    private Integer numberBox;
    private Integer weight;
    private Integer amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private LocalDate filterDate;
}
