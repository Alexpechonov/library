package com.library.dao.repository.instruction;

import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.repository.core.GenericManagerImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 21.07.2017.
 */
@Repository
public class InstructionManagerImpl extends GenericManagerImpl<Instruction> implements InstructionManager {
    @Override
    protected Class<Instruction> getModelClass() {
        return Instruction.class;
    }
}
