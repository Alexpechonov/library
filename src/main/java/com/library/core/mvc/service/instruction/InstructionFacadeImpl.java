package com.library.core.mvc.service.instruction;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.instruction.Part;
import com.library.dao.model.entities.instruction.Step;
import com.library.dao.model.entities.tag.Tag;
import com.library.dao.repository.instruction.InstructionManager;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.instruction.PartDTO;
import com.library.dto.instruction.StepDTO;
import com.library.dto.tag.TagDTO;
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

    @Autowired
    private InstructionManager manager;

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
        return null;
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

    private Set<Tag> convertTagsToModel(Set<TagDTO> tags) {
        Set<Tag> result = new HashSet<>();
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

    private Set<TagDTO> convertTagsToDTO(Set<Tag> tags) {
        Set<TagDTO> result = new HashSet<>();
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
