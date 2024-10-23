package com.sys.bio.back.models.item;

import com.sys.bio.back.models.enums.StoreMovement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private StoreMovement movement;
    private LocalDate date;
    private LocalTime hour;

}
