package com.sys.bio.back.models.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sys.bio.back.models.order.OrderProduct;
import com.sys.bio.back.models.packaging.BoxFormat;
import com.sys.bio.back.models.packaging.BoxName;
import com.sys.bio.back.models.packaging.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderProduct> orderProducts;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Provider provider;
    @ManyToOne(fetch = FetchType.EAGER)
    private BoxName boxName;
    @ManyToOne(fetch = FetchType.EAGER)
    private BoxFormat boxFormat;

    private Integer price;

    private Integer strawAmount;
    private Integer currentAmount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Inventory> inventories = new LinkedHashSet<>();




}
