package com.library.core.mvc.service.instruction;

import com.library.core.mvc.service.core.GenericServiceImpl;
import com.library.dto.instruction.InstructionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
@Service
public class InstructionServiceImpl extends GenericServiceImpl<InstructionFacade, InstructionDTO>
        implements InstructionService {

    @Autowired
    private InstructionFacade facade;

    @Override
    protected InstructionFacade getFacade() {
        return facade;
    }

    @Override
    public List<InstructionDTO> findAllByUser(Long userId) {
        return facade.findAllByUser(userId);
    }

    @Override
    public List<InstructionDTO> findAllByTag(Long tagId) {
        return facade.findAllByTag(tagId);
    }

    @Override
    public List<InstructionDTO> findAllSortedByCreatedDate(int count) {
        return facade.findAllSortByCreationDate(count);
    }

    @Override
    public List<InstructionDTO> getPopular(int count) {
        return facade.getPopular(count);
    }

    @Override
    public List<InstructionDTO> findAllByCategory(Long categoryId) {
        return facade.findAllByCategory(categoryId);
    }
}
