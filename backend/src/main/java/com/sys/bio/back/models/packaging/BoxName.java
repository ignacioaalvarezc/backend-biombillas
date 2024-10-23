package com.sys.bio.back.models.packaging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sys.bio.back.models.item.Product;
import com.sys.bio.back.models.user.Responsible;
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
@Table(name = "boxNames")
public class BoxName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boxNameId;

    private String name;
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("provider")
    private Provider provider;

    @OneToMany(mappedBy = "boxName", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BoxFormat> boxFormats = new LinkedHashSet<>();

    @OneToMany(mappedBy = "boxName", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Package> packages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "boxName", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products = new LinkedHashSet<>();


}
