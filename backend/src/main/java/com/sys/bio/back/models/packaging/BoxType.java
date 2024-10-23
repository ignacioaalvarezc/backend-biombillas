package com.sys.bio.back.models.packaging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sys.bio.back.models.order.Order;
import com.sys.bio.back.models.packaging.Packaging;
import com.sys.bio.back.models.sized.StrawType;
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
@Table(name="boxTypes")

public class BoxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boxTypeId;

    private String description;


}
