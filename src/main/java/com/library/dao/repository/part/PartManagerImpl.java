package com.library.dao.repository.part;

import com.library.dao.model.entities.instruction.Part;
import com.library.dao.model.entities.instruction.QPart;
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
public class PartManagerImpl extends GenericManagerImpl<Part> implements PartManager {
    @Override
    protected Class<Part> getModelClass() {
        return Part.class;
    }

    @Override
    public List<BigInteger> search(String string) {
        Query query = entityManager.createNativeQuery("SELECT id FROM parts WHERE " +
                "data @@ to_tsquery('" + string + "') AND part_type = 'TYPE_TEXT'");
        return query.getResultList();
    }

    @Override
    public BigInteger getInstructionId(Long id) {
        Query query = entityManager.createNativeQuery("SELECT instruction_id FROM steps WHERE " +
                "id = (SELECT p.step_id FROM parts p WHERE p.id = " + id + ")");
        return (BigInteger)query.getSingleResult();
    }

    @Override
    public String getData(Long id) {
        QPart part = QPart.part;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(part).where(part.id.eq(id));
        return query.singleResult(part.data);
    }


}
