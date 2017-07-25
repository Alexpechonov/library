package com.library.core.mvc.service.instruction;

import com.library.core.mvc.service.core.GenericServiceImpl;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.instruction.InstructionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 21.07.2017.
 */
@Service
public class InstructionServiceImpl extends GenericServiceImpl<InstructionFacade, InstructionDTO>
        implements InstructionService {

    @Autowired
    private InstructionFacade facade;

    @Override
    protected InstructionFacade getFacade() { return facade; }
}
