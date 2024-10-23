package com.sys.bio.back.models.sized;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sys.bio.back.models.sanitized.SanitizedBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "strawtypes")
public class StrawType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long strawTypeId;

    private String name;
    private Double factor;
    private String description;
    private Boolean enabled;

    @OneToMany(mappedBy = "strawType", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SizedBox> sizedBoxes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "strawType", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SanitizedBox> sanitizedBoxes = new LinkedHashSet<>();

    public StrawType(String name) { this.name = name; }

}
