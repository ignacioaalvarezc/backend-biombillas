package com.sys.bio.back.models.packaging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sys.bio.back.models.activity.ExtraTask;
import com.sys.bio.back.models.item.Product;
import com.sys.bio.back.models.sized.SizedBox;
import com.sys.bio.back.models.sized.Sizing;
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
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerId;

    private String name;
    private Boolean enabled;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BoxName> boxNames = new LinkedHashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Package> packages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products = new LinkedHashSet<>();


}
