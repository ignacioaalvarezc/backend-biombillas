package com.sys.bio.back.models.sanitized;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sys.bio.back.models.sized.StrawType;
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
@Table(name = "sanitizedBoxes")
public class SanitizedBox {

    @Id
    private Long sanitizedBoxId;

    private Integer numberBox;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate filterDate;


    private Double hoursBetweenDryingBeginAndEnding;

    private Integer dryWeight;
    private LocalTime beginSanitizedTime;
    private LocalTime endSanitizedTime;
    private Integer wetWeight;
    private LocalTime beginDryingTime;
    private LocalTime endDryingTime;
    private Integer dryWeightFinal;

    private Double weightLossPercentage; //Porcentaje de aumento de peso: Fórmula: ((Peso del producto seco después de ser mojado - Peso del producto seco antes de ser mojado) / Peso del producto seco antes de ser mojado) * 100 || Esta métrica muestra el incremento porcentual del peso del producto después de ser mojado en relación con su peso original.
    private Double waterRetentionPercentage; //Porcentaje de retención de agua: Fórmula: ((Peso del producto seco después de ser mojado - Peso del producto seco antes de ser mojado) / (Peso del producto seco después de ser mojado)) * 100 || Esta métrica indica qué porcentaje del peso del producto después de ser mojado está compuesto por agua.
    private String observations;
    private String state;


    @ManyToOne(fetch = FetchType.EAGER)
    private Responsible responsible;
    @ManyToOne(fetch = FetchType.EAGER)
    private StrawType strawType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("sanitized")
    private Sanitized sanitized;
}
