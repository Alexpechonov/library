package com.library.core.mvc.service.step;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.part.PartFacade;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Part;
import com.library.dao.model.entities.instruction.Step;
import com.library.dao.repository.step.StepManager;
import com.library.dto.instruction.PartDTO;
import com.library.dto.instruction.StepDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 25.07.2017.
 */
@Service
@Transactional
public class StepFacadeImpl extends GenericFacadeImpl<StepManager, StepDTO, Step> implements StepFacade {

    @Autowired
    private StepManager manager;

    @Autowired
    private PartFacade partFacade;

    @Override
    protected StepManager getManager() {
        return manager;
    }

    @Override
    public Step convertToModel(StepDTO dto) {
        Step step = new Step();
        step.setId(dto.getId());
        step.setName(dto.getName());
        step.setParts(convertPartsToModel(dto.getParts()));
        return step;
    }

    private List<Part> convertPartsToModel(List<PartDTO> parts) {
        List<Part> result = new ArrayList<>(0);
        for (PartDTO dto : parts) {
            result.add(partFacade.convertToModel(dto));
        }
        return result;
    }

    @Override
    public StepDTO convertToDTO(Step step) {
        StepDTO dto = new StepDTO();
        dto.setId(step.getId());
        dto.setName(step.getName());
        dto.setParts(convertPartsToDTO(step.getParts()));
        return dto;
    }

    private List<PartDTO> convertPartsToDTO(List<Part> parts) {
        List<PartDTO> result = new ArrayList<>(0);
        for (Part part : parts) {
            result.add(partFacade.convertToDTO(part));
        }
        return result;
    }
}
