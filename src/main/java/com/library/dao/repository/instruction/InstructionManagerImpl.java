package com.library.dao.repository.instruction;

import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.instruction.QInstruction;
import com.library.dao.repository.core.GenericManagerImpl;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
}
