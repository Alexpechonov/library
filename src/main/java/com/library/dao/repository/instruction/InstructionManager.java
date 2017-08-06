package com.library.dao.repository.instruction;

import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.repository.core.GenericManager;
import com.library.dto.search.SearchDTO;

import java.math.BigInteger;
import java.util.List;


/**
 * Created by user on 21.07.2017.
 */
public interface InstructionManager extends GenericManager<Instruction> {
    List<Instruction> findAllByUser(Long userId);

    List<Instruction> findAllByCategory(Long categoryId);

    List<BigInteger> findSortedByCreationDate(int count);

    List<BigInteger> getPopular(int count);

    List<BigInteger> search(String string);
}
