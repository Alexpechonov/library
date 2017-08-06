package com.library.core.mvc.service.instruction;

import com.library.core.mvc.service.core.GenericFacade;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.search.SearchDTO;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public interface InstructionFacade extends GenericFacade<InstructionDTO, Instruction> {
    List<InstructionDTO> getInstructionsByIds(List<BigInteger> ids);

    List<InstructionDTO> findAllByUser(Long userId);

    void deleteAllForUser(Long userId);

    List<InstructionDTO> findAllByTag(Long tagId);

    List<InstructionDTO> findAllSortByCreationDate(int count);

    List<InstructionDTO> getPopular(int count);

    List<InstructionDTO> findAllByCategory(Long categoryId);
}
