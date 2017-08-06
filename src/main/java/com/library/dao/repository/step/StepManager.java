package com.library.dao.repository.step;

import com.library.dao.model.entities.instruction.Step;
import com.library.dao.repository.core.GenericManager;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by user on 25.07.2017.
 */
public interface StepManager extends GenericManager<Step> {
    List<BigInteger> search(String string);

    BigInteger getInstructionId(Long id);

    String getName(Long id);
}
