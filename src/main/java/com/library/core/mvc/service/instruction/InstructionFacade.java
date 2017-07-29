package com.library.core.mvc.service.instruction;

import com.library.core.mvc.service.core.GenericFacade;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dto.instruction.InstructionDTO;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public interface InstructionFacade extends GenericFacade<InstructionDTO, Instruction> {

    List<InstructionDTO> findAllByUser(Long userId);
}
