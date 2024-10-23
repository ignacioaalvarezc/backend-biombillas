package com.sys.bio.back.models.packaging;

import com.fasterxml.jackson.annotation.JsonProperty;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "packages")
public class Package {

    @Id
    private Long packageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("packaging")
    private Packaging packaging;

    @ManyToOne(fetch = FetchType.EAGER)
    private Provider provider;
    @ManyToOne(fetch = FetchType.EAGER)
    private BoxName boxName;
    @ManyToOne(fetch = FetchType.EAGER)
    private BoxFormat boxFormat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("responsible")
    private Responsible responsible;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate filterDate;

    private LocalTime hour;

    private Integer boxAmount;

    private Integer strawAmount;

    private Integer weightRejected;
    private Integer strawRejected;
    private String reasonRejected;
    private String state;

}
