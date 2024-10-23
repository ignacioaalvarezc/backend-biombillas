package com.sys.bio.back.models.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sys.bio.back.models.packaging.BoxType;
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
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

    @Id
    private Long orderId;
    private String orderNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderProduct> orderProducts = new LinkedHashSet<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pickUpDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String  indications;

    private String state;
}
