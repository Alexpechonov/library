package com.library.dao.repository.instruction;

import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.instruction.QInstruction;
import com.library.dao.repository.core.GenericManagerImpl;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
@Repository
public class InstructionManagerImpl extends GenericManagerImpl<Instruction> implements InstructionManager {
    @Override
    protected Class<Instruction> getModelClass() {
        return Instruction.class;
    }

    @Override
    public List<Instruction> findAllByUser(Long userId) {
        QInstruction instruction = QInstruction.instruction;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(instruction).where(instruction.user().id.eq(userId));
        return query.list(instruction);
    }

    @Override
    public List<Instruction> findAllByCategory(Long categoryId) {
        QInstruction instruction = QInstruction.instruction;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(instruction).where(instruction.category().id.eq(categoryId));
        return query.list(instruction);
    }

    @Override
    public List<BigInteger> findSortedByCreationDate(int count) {
        Query query = entityManager.createNativeQuery("SELECT id FROM instructions ORDER BY CREATION_DATE DESC");
        query.setMaxResults(count);
        return query.getResultList();
    }

    @Override
    public List<BigInteger> getPopular(int count) {
        Query query = entityManager.createNativeQuery("SELECT i.id FROM instructions i " +
                "ORDER BY (SELECT count(r) FROM ratings r WHERE i.id = r.instruction_id) DESC");
        query.setMaxResults(count);
        return query.getResultList();
    }

    @Override
    public List<BigInteger> search(String string) {
        Query query = entityManager.createNativeQuery("SELECT id FROM instructions WHERE " +
                "name @@ to_tsquery('" + string + "')");
        return query.getResultList();
    }
}
