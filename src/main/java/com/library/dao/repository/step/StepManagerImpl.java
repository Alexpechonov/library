package com.library.dao.repository.step;

import com.library.dao.model.entities.instruction.QPart;
import com.library.dao.model.entities.instruction.QStep;
import com.library.dao.model.entities.instruction.Step;
import com.library.dao.repository.core.GenericManagerImpl;
import com.library.dto.search.SearchDTO;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by user on 25.07.2017.
 */
@Repository
public class StepManagerImpl extends GenericManagerImpl<Step> implements StepManager {
    @Override
    protected Class<Step> getModelClass() {
        return Step.class;
    }

    @Override
    public List<BigInteger> search(String string) {
        Query query = entityManager.createNativeQuery("SELECT id FROM steps WHERE " +
                "name @@ to_tsquery('" + string + "')");
        return query.getResultList();
    }

    @Override
    public BigInteger getInstructionId(Long id) {
        Query query = entityManager.createNativeQuery("SELECT instruction_id FROM steps WHERE id = " + id);
        return (BigInteger)query.getSingleResult();
    }

    @Override
    public String getName(Long id) {
        QStep step = QStep.step;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(step).where(step.id.eq(id));
        return query.singleResult(step.name);
    }
}
