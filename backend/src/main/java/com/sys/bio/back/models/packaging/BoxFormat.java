package com.sys.bio.back.models.packaging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sys.bio.back.models.item.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "boxFormats")
public class BoxFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boxFormatId;

    private String name;
    private Integer amount;
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("boxName")
    private BoxName boxName;

    @OneToMany(mappedBy = "boxFormat", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Package> packages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "boxFormat", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products = new LinkedHashSet<>();



}
