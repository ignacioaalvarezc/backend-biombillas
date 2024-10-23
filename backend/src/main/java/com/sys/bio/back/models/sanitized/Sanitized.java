package com.sys.bio.back.models.sanitized;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sys.bio.back.models.user.Responsible;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sanitizeds")
public class Sanitized {

    @Id
    private Long sanitizedId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Responsible responsible;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Integer totalHours;

    private String observations;

    private String state;
    private String stateBoxes;

    private LocalTime startTime;
    private LocalTime endTime;

    @OneToMany(mappedBy = "sanitized", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SanitizedBox> sanitizedBoxes = new LinkedHashSet<>();

}
