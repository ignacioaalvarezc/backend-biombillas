package com.sys.bio.back.models.cutting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sys.bio.back.models.activity.ExtraTask;
import com.sys.bio.back.models.user.Responsible;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuttings")
public class Cutting {

    @Id
    private Long cuttingId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Responsible responsible;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer totalWeight;
    private Integer totalAmount;

    private LocalTime startTime;
    private LocalTime endTime;

    private Double totalHours;

    private String observations;

    private LocalDate filterDate;

    private String state;

    @OneToMany(mappedBy = "cutting", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CutBox> cutBoxes = new LinkedHashSet<>();

}
