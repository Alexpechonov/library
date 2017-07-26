package com.library.core.mvc.service.part;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.user.UserFacade;
import com.library.dao.model.entities.instruction.Part;
import com.library.dao.repository.instruction.InstructionManager;
import com.library.dao.repository.part.PartManager;
import com.library.dao.repository.step.StepManager;
import com.library.dto.instruction.PartDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by user on 25.07.2017.
 */
@Service
@Transactional
public class PartFacadeImpl extends GenericFacadeImpl<PartManager, PartDTO, Part> implements PartFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartFacadeImpl.class);

    @Autowired
    private PartManager manager;

    @Autowired
    private StepManager stepManager;

    @Autowired
    private InstructionManager instructionManager;

    @Autowired
    private UserFacade userFacade;

    @Override
    protected PartManager getManager() {
        return manager;
    }

    @Override
    public Part convertToModel(PartDTO dto) {
        Part part = new Part();
        part.setId(dto.getId());
        part.setData(dto.getData());
        part.setType(dto.getType());
        return part;
    }

    @Override
    public PartDTO convertToDTO(Part part) {
        PartDTO dto = new PartDTO();
        dto.setId(part.getId());
        dto.setData(part.getData());
        dto.setType(part.getType());
        return dto;
    }
}
