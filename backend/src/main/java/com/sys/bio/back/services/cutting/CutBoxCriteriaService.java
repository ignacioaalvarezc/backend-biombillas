package com.sys.bio.back.services.cutting;

import com.sys.bio.back.criteria.CutBoxCriteria;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.user.Responsible;
import com.sys.bio.back.models.user.Responsible_;
import com.sys.bio.back.models.cutting.CutBox_;
import com.sys.bio.back.models.cutting.CutType_;
import com.sys.bio.back.repositories.cutting.CutBoxRepository;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CutBoxCriteriaService extends QueryService<CutBox> {

    @Autowired
    CutBoxRepository boxRepo;

    public List<CutBox> findByCriteria(CutBoxCriteria boxCriteria) {
        final Specification<CutBox> specification = createSpecification(boxCriteria);
        List<CutBox> cutBoxes = boxRepo.findAll(specification);
        return cutBoxes;
    }

    private Specification<CutBox> createSpecification(CutBoxCriteria criteria) {
        Specification<CutBox> specification = Specification.where(null);
        if (criteria != null) {
            if(criteria.getResponsible() != null) {
                specification =
                        specification.and(buildReferringEntitySpecification(criteria.getResponsible(), CutBox_.responsible, Responsible_.name));
            }
            if(criteria.getCutType() != null) {
                specification =
                        specification.and(buildReferringEntitySpecification(criteria.getCutType(), CutBox_.cutType, CutType_.name));
            }
            if(criteria.getWeight() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getWeight(), CutBox_.weight));
            }
            if(criteria.getAmount() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getAmount(), CutBox_.amount));
            }
            if (criteria.getFilterDate() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getFilterDate(), CutBox_.filterDate));
            }
        }
        return specification;
    }


}
