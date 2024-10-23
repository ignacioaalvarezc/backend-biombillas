package com.sys.bio.back.services.sized;


import com.sys.bio.back.criteria.SizingCriteria;
import com.sys.bio.back.models.sized.Sizing;
import com.sys.bio.back.models.sized.Sizing_;
import com.sys.bio.back.models.user.Responsible_;
import com.sys.bio.back.repositories.sized.SizingRepository;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SizingCriteriaService extends QueryService<Sizing> {

    @Autowired
    SizingRepository sizingRepo;

    public List<Sizing> findByCriteria(SizingCriteria sizingCriteria) {
        final Specification<Sizing> specification = createSpecification(sizingCriteria);
        List<Sizing> sizings = sizingRepo.findAll(specification);
        return sizings;
    }

    private Specification<Sizing> createSpecification(SizingCriteria criteria) {
        Specification<Sizing> specification = Specification.where(null);
        if (criteria != null) {
            if(criteria.getResponsible() != null) {
                specification =
                        specification.and(buildReferringEntitySpecification(criteria.getResponsible(), Sizing_.responsible, Responsible_.name));
            }
            if(criteria.getTotalWeight() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getTotalWeight(), Sizing_.totalWeight));
            }
            if(criteria.getTotalAmount() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getTotalAmount(), Sizing_.totalAmount));
            }
            if(criteria.getTotalHours() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getTotalHours(), Sizing_.totalHours));
            }
            if (criteria.getFilterDate() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getFilterDate(), Sizing_.filterDate));
            }
        }
        return specification;
    }
}
