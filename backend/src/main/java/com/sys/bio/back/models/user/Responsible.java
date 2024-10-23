package com.sys.bio.back.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sys.bio.back.models.activity.ExtraTask;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.models.packaging.Packaging;
import com.sys.bio.back.models.reception.Reception;
import com.sys.bio.back.models.sized.SizedBox;
import com.sys.bio.back.models.sized.Sizing;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "responsibles")
public class Responsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responsibleId;

    private String name;
    private String email;
    private boolean enabled = true;

    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reception> receptions = new LinkedHashSet<>();
    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Cutting> cuttings = new LinkedHashSet<>();
    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ExtraTask> extraTasks = new LinkedHashSet<>();
    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CutBox> cutBoxes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SizedBox> sizedBoxes = new LinkedHashSet<>();
    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Sizing> sizings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Packaging> packagings = new LinkedHashSet<>();

}
