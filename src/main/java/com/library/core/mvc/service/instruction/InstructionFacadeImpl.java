package com.library.core.mvc.service.instruction;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.user.UserFacadeImpl;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.instruction.Part;
import com.library.dao.model.entities.instruction.Step;
import com.library.dao.model.entities.tag.Tag;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.instruction.InstructionManager;
import com.library.dao.repository.user.UserManager;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.instruction.PartDTO;
import com.library.dto.instruction.StepDTO;
import com.library.dto.tag.TagDTO;
import com.library.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 21.07.2017.
 */
@Service
@Transactional
public class InstructionFacadeImpl extends GenericFacadeImpl<InstructionManager, InstructionDTO, Instruction>
        implements InstructionFacade {

    private static final Logger logger = LoggerFactory.getLogger(InstructionFacadeImpl.class);

    @Autowired
    private InstructionManager manager;

    @Autowired
    private UserManager userManager;

    @Override
    protected InstructionManager getManager() { return manager; }

    @Override
    public Instruction convertToModel(InstructionDTO dto) {
        Instruction result = new Instruction();
        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setCreationDate(dto.getCreationDate());
        result.setLastModifiedDate(dto.getLastModifiedDate());
        result.setSteps(convertStepsToModel(dto.getSteps()));
        result.setTags(convertTagsToModel(dto.getTags()));
        User user = null;
        try {
            user = userManager.findById(dto.getUser().getId());
        } catch (ManagerException e) {
            logger.error("error in UserManager.findById", e);
        }
        result.setUser(user);
        return result;
    }

    @Override
    public InstructionDTO convertToDTO(Instruction instruction) {
        InstructionDTO dto = new InstructionDTO();
        dto.setId(instruction.getId());
        dto.setName(instruction.getName());
        dto.setLastModifiedDate(instruction.getLastModifiedDate());
        dto.setCreationDate(instruction.getCreationDate());
        dto.setSteps(convertStepsToDTO(instruction.getSteps()));
        dto.setTags(convertTagsToDTO(instruction.getTags()));
        UserDTO userDTO = new UserDTO();
        userDTO.setId(instruction.getUser().getId());
        userDTO.setUserName(instruction.getUser().getUserName());
        userDTO.setRole(instruction.getUser().getRole());
        userDTO.setEnabled(instruction.getUser().isEnabled());
        userDTO.setAbout(instruction.getUser().getAbout());
        userDTO.setFirstName(instruction.getUser().getFirstName());
        userDTO.setLastName(instruction.getUser().getLastName());
        userDTO.setImage(instruction.getUser().getImage());
        return dto;
    }

    private List<Step> convertStepsToModel(List<StepDTO> steps) {
        List<Step> result = new ArrayList<>(0);
        for (StepDTO dto : steps) {
            result.add(convertStepToModel(dto));
        }
        return result;
    }

    private Step convertStepToModel(StepDTO dto) {
        Step step = new Step();
        step.setId(dto.getId());
        step.setParts(convertPartsToModel(dto.getParts()));
        return step;
    }

    private List<Part> convertPartsToModel(List<PartDTO> parts) {
        List<Part> result = new ArrayList<>(0);
        for (PartDTO dto : parts) {
            result.add(convertPartToModel(dto));
        }
        return result;
    }

    private Part convertPartToModel(PartDTO dto) {
        Part part = new Part();
        part.setId(dto.getId());
        part.setData(dto.getData());
        part.setType(dto.getType());
        return part;
    }

    private List<Tag> convertTagsToModel(List<TagDTO> tags) {
        List<Tag> result = new ArrayList<>();
        for (TagDTO dto : tags) {
            result.add(convertTagToModel(dto));
        }
        return result;
    }

    private Tag convertTagToModel(TagDTO dto) {
        Tag tag = new Tag();
        tag.setId(dto.getId());
        tag.setName(dto.getName());
        return tag;
    }

    private List<StepDTO> convertStepsToDTO(List<Step> steps) {
        List<StepDTO> result = new ArrayList<>(0);
        for (Step step : steps) {
            result.add(convertStepToDTO(step));
        }
        return result;
    }

    private StepDTO convertStepToDTO(Step step) {
        StepDTO dto = new StepDTO();
        dto.setId(step.getId());
        dto.setParts(convertPartsToDTO(step.getParts()));
        return dto;
    }

    private List<PartDTO> convertPartsToDTO(List<Part> parts) {
        List<PartDTO> result = new ArrayList<>(0);
        for (Part part : parts) {
            result.add(convertPartToDTO(part));
        }
        return result;
    }

    private PartDTO convertPartToDTO(Part part) {
        PartDTO dto = new PartDTO();
        part.setId(part.getId());
        part.setData(part.getData());
        part.setType(part.getType());
        return dto;
    }

    private List<TagDTO> convertTagsToDTO(List<Tag> tags) {
        List<TagDTO> result = new ArrayList<>();
        for (Tag tag : tags) {
            result.add(convertTagToDTO(tag));
        }
        return result;
    }

    private TagDTO convertTagToDTO(Tag tag) {
        TagDTO dto = new TagDTO();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        return dto;
    }
}