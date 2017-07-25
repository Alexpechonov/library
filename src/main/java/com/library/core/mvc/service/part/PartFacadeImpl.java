package com.library.core.mvc.service.part;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.exception.AccessException;
import com.library.core.mvc.service.user.UserFacade;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.instruction.Part;
import com.library.dao.model.entities.instruction.PartType;
import com.library.dao.model.entities.instruction.Step;
import com.library.dao.repository.instruction.InstructionManager;
import com.library.dao.repository.part.PartManager;
import com.library.dao.repository.step.StepManager;
import com.library.dto.instruction.PartDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public PartDTO update(PartDTO dto, Long id) throws AccessException, ManagerException {
        Instruction instruction = null;
        try {
            instruction = instructionManager.findById(id);
            if(instruction.getUser().getId() != userFacade.getMe().getId()) {
                throw new AccessException("Its not yours instruction");
            }
        } catch (ManagerException e) {
            LOGGER.error("Error in InstructionManager.findById()");
        }
        if(checkExist(instruction, dto.getId()) == true) {
            return super.update(dto);
        }
        return dto;

    }

    @Override
    public List<PartDTO> getUpdatedImages(List<PartDTO> parts) throws ManagerException{
        for(PartDTO dto: parts) {
            if(dto.getType().equals(PartType.TYPE_IMAGE) && dto.getId() != null) {
                dto.setData(manager.findById(dto.getId()).getData());
            }
        }
        return parts;
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

    private boolean checkExist(Instruction instruction, Long id) {
        for(Step step: instruction.getSteps()) {
            for(Part part: step.getParts()) {
                if(part.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }
}
