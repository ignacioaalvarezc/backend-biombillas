package com.sys.bio.back.models.sanitized;

import com.sys.bio.back.models.sized.SizedBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
    private List<Long> ids;
    private List<SanitizedBox> sanitizedBoxes;

}
