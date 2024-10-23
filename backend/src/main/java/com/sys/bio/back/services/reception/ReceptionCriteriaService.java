package com.sys.bio.back.services.reception;

import com.sys.bio.back.criteria.ReceptionCriteria;
import com.sys.bio.back.models.user.Responsible_;
import com.sys.bio.back.models.reception.Reception;
import com.sys.bio.back.models.reception.Reception_;
import com.sys.bio.back.repositories.reception.ReceptionRepository;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReceptionCriteriaService extends QueryService<Reception> {

    @Autowired
    ReceptionRepository receptionRepository;

    public List<Reception> findByCriteria(ReceptionCriteria receptionCriteria) {
        final Specification<Reception> specification = createSpecification(receptionCriteria);
        List<Reception> receptions = receptionRepository.findAll(specification);
        return receptions;
    }

    private Specification<Reception> createSpecification(ReceptionCriteria criteria) {
        Specification<Reception> specification = Specification.where(null);
        if(criteria != null) {
            if(criteria.getAcceptedBales() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getAcceptedBales(), Reception_.acceptedBales));
            }
            if(criteria.getRejectedBales() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getRejectedBales(), Reception_.rejectedBales));
            }
            if(criteria.getResponsible() != null) {
                specification =
                        specification.and(buildReferringEntitySpecification(criteria.getResponsible(), Reception_.responsible, Responsible_.name));
            }
            if (criteria.getFilterDate() != null) {
                specification =
                        specification.and(buildRangeSpecification(criteria.getFilterDate(), Reception_.filterDate));
            }
            /*if(criteria.getMarca() != null) {
                specification =
                    specification.and(buildSpecification(criteria.getMarca(),
                        root->root.join(Coche_.modelo, JoinType.LEFT)
                            .join(Modelo_.marca, JoinType.LEFT).get(Marca_.nombre)));
                            }

             */
        }
        return specification;
    }

}
