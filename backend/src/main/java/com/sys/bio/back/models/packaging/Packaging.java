package com.sys.bio.back.models.packaging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.user.Responsible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="packagings")
public class Packaging {

    @Id
    private Long packagingId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Responsible responsible;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double totalHours;
    private Integer totalBoxes;
    private Integer totalAmount;
    private String observations;
    private String state;

    @OneToMany(mappedBy = "packaging", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Package> packages = new LinkedHashSet<>();
}
