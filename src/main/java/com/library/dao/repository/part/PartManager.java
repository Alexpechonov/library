package com.library.dao.repository.part;

import com.library.dao.model.entities.instruction.Part;
import com.library.dao.repository.core.GenericManager;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by user on 25.07.2017.
 */
public interface PartManager extends GenericManager<Part> {
    List<BigInteger> search(String string);

    BigInteger getInstructionId(Long id);

    String getData(Long id);
}
