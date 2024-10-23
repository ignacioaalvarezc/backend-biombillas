package com.sys.bio.back.services.cutting;

import com.sys.bio.back.criteria.CutBoxCriteria;
import com.sys.bio.back.criteria.CuttingCriteria;
import com.sys.bio.back.models.cutting.*;
import com.sys.bio.back.models.user.Responsible_;
import com.sys.bio.back.repositories.cutting.CuttingRepository;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CuttingCriteriaService extends QueryService<Cutting> {

    @Autowired
    CuttingRepository cuttingRepo;

    public List<Cutting> findByCriteria(CuttingCriteria cuttingCriteria) {
        final Specification<Cutting> specification = createSpecification(cuttingCriteria);
        List<Cutting> cuttings = cuttingRepo.findAll(specification);
        return cuttings;
    }

    private Specification<Cutting> createSpecification(CuttingCriteria criteria) {
        Specification<Cutting> specification = Specification.where(null);
        if (criteria != null) {
            if(criteria.getResponsible() != null) {
                specification =
                        specification.and(buildReferringEntitySpecification(criteria.getResponsible(), Cutting_.responsible, Responsible_.name));
            }
            if(criteria.getTotalWeight() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getTotalWeight(), Cutting_.totalWeight));
            }
            if(criteria.getTotalAmount() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getTotalAmount(), Cutting_.totalAmount));
            }
            if(criteria.getTotalHours() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getTotalHours(), Cutting_.totalHours));
            }
            if (criteria.getFilterDate() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getFilterDate(), Cutting_.filterDate));
            }
        }
        return specification;
    }
}
