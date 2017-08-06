package com.library.core.mvc.service.search;

import com.library.core.mvc.service.instruction.InstructionFacade;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.repository.comment.CommentManager;
import com.library.dao.repository.instruction.InstructionManager;
import com.library.dao.repository.part.PartManager;
import com.library.dao.repository.step.StepManager;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.search.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 04.08.2017.
 */
@Service
@Transactional
public class SearchFacadeImpl implements SearchFacade {

    private static final Logger logger = LoggerFactory.getLogger(SearchFacadeImpl.class);

    @Autowired
    private InstructionManager instructionManager;

    @Autowired
    private InstructionFacade instructionFacade;

    @Autowired
    private PartManager partManager;

    @Autowired
    private StepManager stepManager;

    @Autowired
    private CommentManager commentManager;

    @Override
    public List<SearchDTO> search(String string) {
        string = string.replaceAll(" +", " | ");
        List<SearchDTO> results = new ArrayList<>();
        results.addAll(searchInstructions(string));
        results.addAll(searchSteps(string));
        results.addAll(searchParts(string));
        results.addAll(searchComments(string));
        return results;
    }

    private List<SearchDTO> searchSteps(String string) {
        try {
            return convertFromStepIdList(stepManager.search(string));
        } catch (ManagerException e) {
            logger.error("Error in SearchFacade.searchSteps bad instruction id");
        }
        return null;
    }

    private List<SearchDTO> convertFromStepIdList(List<BigInteger> step_ids) throws ManagerException {
        List<SearchDTO> searchResults = new ArrayList<>();
        for (BigInteger stepId : step_ids) {
            SearchDTO newDto = new SearchDTO();
            newDto.setInstruction(instructionFacade.convertToDTO(instructionManager.findById(stepManager.
                    getInstructionId(stepId.longValue()).longValue())));
            newDto.setData(stepManager.getName(stepId.longValue()));
            searchResults.add(newDto);
        }
        return searchResults;
    }

    private List<SearchDTO> searchParts(String string) {
        try {
            return convertFromPartIdList(partManager.search(string));
        } catch (ManagerException e) {
            logger.error("Error in SearchFacade.searchSteps bad instruction id");
        }
        return null;
    }

    private List<SearchDTO> convertFromPartIdList(List<BigInteger> part_ids) throws ManagerException {
        List<SearchDTO> searchResults = new ArrayList<>();
        for (BigInteger partId : part_ids) {
            SearchDTO newDto = new SearchDTO();
            newDto.setInstruction(instructionFacade.convertToDTO(instructionManager.findById(partManager.
                    getInstructionId(partId.longValue()).longValue())));
            newDto.setData(partManager.getData(partId.longValue()));
            searchResults.add(newDto);
        }
        return searchResults;
    }

    private List<SearchDTO> searchComments(String string) {
        try {
            return convertFromCommentIdList(commentManager.search(string));
        } catch (ManagerException e) {
            logger.error("Error in SearchFacade.searchSteps bad instruction id");
        }
        return null;
    }

    private List<SearchDTO> convertFromCommentIdList(List<BigInteger> comment_ids) throws ManagerException {
        List<SearchDTO> searchResults = new ArrayList<>();
        for (BigInteger commentId : comment_ids) {
            SearchDTO newDto = new SearchDTO();
            newDto.setInstruction(instructionFacade.convertToDTO(instructionManager.findById(commentManager.
                    getInstructionId(commentId.longValue()).longValue())));
            newDto.setData(commentManager.getText(commentId.longValue()));
            searchResults.add(newDto);
        }
        return searchResults;
    }

    private List<SearchDTO> searchInstructions(String string) {
        return convertFromInstructionDTOList(instructionFacade.getInstructionsByIds(instructionManager.search(string)));
    }

    private List<SearchDTO> convertFromInstructionDTOList(List<InstructionDTO> instructions) {
        List<SearchDTO> searchResults = new ArrayList<>();
        for (InstructionDTO instruction : instructions) {
            SearchDTO newDto = new SearchDTO();
            newDto.setInstruction(instruction);
            newDto.setData(instruction.getName());
            searchResults.add(newDto);
        }
        return searchResults;
    }
}
