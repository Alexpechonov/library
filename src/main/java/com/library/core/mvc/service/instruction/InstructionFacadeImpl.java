package com.library.core.mvc.service.instruction;

import com.library.core.mvc.service.category.CategoryFacade;
import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.core.mvc.service.medal.MedalFacade;
import com.library.core.mvc.service.step.StepFacade;
import com.library.core.mvc.service.tag.TagFacade;
import com.library.core.mvc.service.user.UserFacade;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.category.Category;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.instruction.Part;
import com.library.dao.model.entities.instruction.Step;
import com.library.dao.model.entities.tag.Tag;
import com.library.dao.model.entities.user.Role;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.category.CategoryManager;
import com.library.dao.repository.comment.CommentManager;
import com.library.dao.repository.instruction.InstructionManager;
import com.library.dao.repository.rating.RatingManager;
import com.library.dao.repository.tag.TagManager;
import com.library.dao.repository.user.UserManager;
import com.library.dto.category.CategoryDTO;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.instruction.StepDTO;
import com.library.dto.tag.TagDTO;
import com.library.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

    @Autowired
    private TagFacade tagFacade;

    @Autowired
    private StepFacade stepFacade;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CategoryFacade categoryFacade;

    @Autowired
    private CommentManager commentManager;

    @Autowired
    private RatingManager ratingManager;

    @Autowired
    private MedalFacade medalFacade;

    @Autowired
    private TagManager tagManager;

    @Override
    protected InstructionManager getManager() {
        return manager;
    }

    @Override
    public InstructionDTO insert(InstructionDTO dto) throws ServiceException {
        dto.setCreationDate(new Date());
        dto.setLastModifiedDate(new Date());
        dto.setCategory(categoryFacade.findByName("In progress"));
        return super.insert(dto);
    }

    protected void afterInsert(Instruction instruction) throws ServiceException {
        medalFacade.checkInstructions();
    }


    @Override
    public InstructionDTO update(InstructionDTO dto) throws ManagerException {
        return convertToDTO(manager.update(beforeUpdate(dto)));
    }

    @Override
    public List<InstructionDTO> findAllByUser(Long userId) {
        return convertToDTOList(manager.findAllByUser(userId));
    }

    @Override
    public void deleteAllForUser(Long userId) {
        List<Instruction> instructions = manager.findAllByUser(userId);
        for (Instruction instruction : instructions) {
            remove(instruction.getId());
        }
    }

    @Override
    public List<InstructionDTO> findAllByTag(Long tagId) {
        return convertToDTOList(getByTag(manager.findAll(), tagId));
    }

    private List<Instruction> getByTag(List<Instruction> instructions, Long tagId) {
        for (Iterator<Instruction> iterator = instructions.iterator(); iterator.hasNext(); ) {
            Instruction instruction = iterator.next();
            if (!checkTag(instruction, tagId)) iterator.remove();
        }
        return instructions;
    }

    private boolean checkTag(Instruction instruction, Long tagId) {
        for (Tag tag : instruction.getTags()) {
            if (tag.getId().equals(tagId)) return true;
        }
        return false;
    }

    private Instruction beforeUpdate(InstructionDTO dto) throws ManagerException {
        dto.setUser(getAuthor(dto.getId()));
        dto.setLastModifiedDate(new Date());
        tagFacade.insertListIfNotExist(dto.getTags());
        dto.setTags(tagFacade.getUpdatedTags(dto.getTags()));
        return addPositions(convertToModel(dto));
    }

    private UserDTO getAuthor(Long instructionId) throws ManagerException {
        User author = userManager.findById(manager.findById(instructionId).getUser().getId());
        if (!author.getId().equals(userFacade.getMe().getId()) && userFacade.getMe().getRole() != Role.ROLE_ADMIN) {
            throw new ManagerException("You can't manage this instruction");
        }
        return userFacade.convertToDTO(author);
    }

    private Instruction addPositions(Instruction instruction) {
        for (int i = 0; i < instruction.getSteps().size(); i++) {
            instruction.getSteps().get(i).setPosition(i);
            for (int j = 0; j < instruction.getSteps().get(i).getParts().size(); j++) {
                instruction.getSteps().get(i).getParts().get(j).setPosition(j);
            }
        }
        return instruction;
    }

    private void checkAccess(Long instructionId) throws ServiceException {
        try {
            getAuthor(instructionId);
        } catch (ManagerException e) {
            throw new ServiceException("You can't delete this instruction");
        }
    }

    private void stepsBeforeDelete(Long id) {
        try {
            for (Step step : manager.findById(id).getSteps()) {
                commentManager.deleteAllForStep(step.getId());
            }
        } catch (ManagerException e) {
            throw new ServiceException("Bad id");
        }
    }

    private void deleteLinkWIthTags(Long instructionId) throws ServiceException {
        try {
            Instruction instruction = manager.findById(instructionId);
            instruction.setTags(new ArrayList<>());
            manager.update(instruction);
        } catch (ManagerException e) {
            throw new ServiceException("Error in InstructionFacade.deleteLinks");
        }
    }

    @Override
    protected void beforeDelete(Long id) throws ServiceException {
        checkAccess(id);
        stepsBeforeDelete(id);
        deleteLinkWIthTags(id);
        ratingManager.deleteAllForInstruction(id);
    }

    @Override
    public InstructionDTO findById(Long id) throws ManagerException {
        Instruction instruction = manager.findById(id);
        instruction = sortSteps(instruction);
        instruction = sortParts(instruction);
        return convertToDTO(instruction);
    }

    @Override
    public List<InstructionDTO> findAllSortByCreationDate(int count) {
        return getInstructionsByIds(manager.findSortedByCreationDate(count));
    }

    @Override
    public List<InstructionDTO> getPopular(int count) {
        return getInstructionsByIds(manager.getPopular(count));
    }

    @Override
    public List<InstructionDTO> findAllByCategory(Long categoryId) {
        return convertToDTOList(manager.findAllByCategory(categoryId));
    }

    @Override
    public List<InstructionDTO> getInstructionsByIds(List<BigInteger> ids) {
        List<InstructionDTO> instructions = new ArrayList<>();
        for (BigInteger id: ids) {
            try {
                instructions.add(findById(id.longValue()));
            } catch (ManagerException e) {
                logger.error("Error in InstructionFacade.findAllSortByCreationDate");
            }
        }
        return instructions;
    }

    private Instruction sortSteps(Instruction instruction) {
        if (instruction.getSteps().size() > 0) {
            Collections.sort(instruction.getSteps(), new Comparator<Step>() {
                @Override
                public int compare(final Step object1, final Step object2) {
                    return object1.getPosition().compareTo(object2.getPosition());
                }
            });
        }
        return instruction;
    }

    private Instruction sortParts(Instruction instruction) {
        for (Step step : instruction.getSteps()) {
            if (step.getParts().size() > 0) {
                Collections.sort(step.getParts(), new Comparator<Part>() {
                    @Override
                    public int compare(final Part object1, final Part object2) {
                        return object1.getPosition().compareTo(object2.getPosition());
                    }
                });
            }
        }
        return instruction;
    }

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
            logger.error("error in InstructionFacade.convertToModel", e);
        }
        result.setUser(user);
        Category category = new Category();
        category.setId(dto.getCategory().getId());
        category.setName(dto.getCategory().getName());
        result.setCategory(category);
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
        dto.setUser(userDTO);
        CategoryDTO category = new CategoryDTO();
        category.setId(instruction.getCategory().getId());
        category.setName(instruction.getCategory().getName());
        dto.setCategory(category);
        return dto;
    }

    private List<Step> convertStepsToModel(List<StepDTO> steps) {
        List<Step> result = new ArrayList<>(0);
        for (StepDTO dto : steps) {
            result.add(stepFacade.convertToModel(dto));
        }
        return result;
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
            result.add(stepFacade.convertToDTO(step));
        }
        return result;
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
