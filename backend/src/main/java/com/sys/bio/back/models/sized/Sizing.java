package com.sys.bio.back.models.sized;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "sizings")
public class Sizing {

    @Id
    private Long sizingId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Responsible responsible;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private LocalDate filterDate;
    private LocalTime hour;
    private Integer totalWeight;
    private Integer totalAmount;
    private Integer totalHours;
    private String observations;
    private String state;
    private LocalTime startTime;
    private LocalTime endTime;

    @OneToMany(mappedBy = "sizing", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SizedBox> sizedBoxes = new LinkedHashSet<>();

}
