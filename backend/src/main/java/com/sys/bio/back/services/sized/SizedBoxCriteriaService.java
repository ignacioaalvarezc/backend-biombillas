package com.sys.bio.back.services.sized;


import com.sys.bio.back.criteria.SizedBoxCriteria;
import com.sys.bio.back.models.sized.SizedBox;
import com.sys.bio.back.models.sized.SizedBox_;
import com.sys.bio.back.models.sized.StrawType_;
import com.sys.bio.back.models.user.Responsible_;
import com.sys.bio.back.repositories.sized.SizedBoxRepository;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SizedBoxCriteriaService extends QueryService<SizedBox> {

    @Autowired
    SizedBoxRepository boxRepo;

    public List<SizedBox> findByCriteria(SizedBoxCriteria boxCriteria) {
        final Specification<SizedBox> specification = createSpecification(boxCriteria);
        List<SizedBox> sizedBoxes = boxRepo.findAll(specification);
        return sizedBoxes;
    }

    private Specification<SizedBox> createSpecification(SizedBoxCriteria criteria) {
        Specification<SizedBox> specification = Specification.where(null);
        if (criteria != null) {
            if(criteria.getResponsible() != null) {
                specification =
                        specification.and(buildReferringEntitySpecification(criteria.getResponsible(), SizedBox_.responsible, Responsible_.name));
            }
            if(criteria.getStrawType() != null) {
                specification =
                        specification.and(buildReferringEntitySpecification(criteria.getStrawType(), SizedBox_.strawType, StrawType_.name));
            }
            if(criteria.getWeight() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getWeight(), SizedBox_.weight));
            }
            if(criteria.getAmount() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getAmount(), SizedBox_.amount));
            }
            if (criteria.getFilterDate() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getFilterDate(), SizedBox_.filterDate));
            }
        }
        return specification;
    }

}
