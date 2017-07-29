package com.library.core.mvc.service.instruction;

import com.library.core.mvc.service.core.GenericService;
import com.library.dto.instruction.InstructionDTO;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public interface InstructionService extends GenericService<InstructionDTO> {
    List<InstructionDTO> findAllByUser(Long userId);
}
