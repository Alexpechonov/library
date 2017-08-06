package com.library.core.mvc.service.category;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.category.Category;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.repository.category.CategoryManager;
import com.library.dao.repository.instruction.InstructionManager;
import com.library.dto.category.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user on 28.07.2017.
 */
@Service
@Transactional
public class CategoryFacadeImpl extends GenericFacadeImpl<CategoryManager, CategoryDTO, Category>
        implements CategoryFacade {

    private static final Logger logger = LoggerFactory.getLogger(CategoryFacadeImpl.class);

    @Autowired
    private CategoryManager manager;

    @Autowired
    private InstructionManager instructionManager;

    @Override
    protected CategoryManager getManager() {
        return manager;
    }

    @Override
    public Category convertToModel(CategoryDTO dto) {
        Category category = new Category();
        if (dto == null) {
            return category;
        }
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    @Override
    public CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        if (category == null) {
            return dto;
        }
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    @Override
    public CategoryDTO findByName(String name) {
        return convertToDTO(manager.findByName(name));
    }

    @Override
    public CategoryDTO insert(CategoryDTO dto) throws ServiceException {
        if(manager.findByName(dto.getName()) != null) {
            return null;
        }
        return super.insert(dto);
    }

    @Override
    protected void beforeDelete(Long id) throws ServiceException {
        removeCategoryFromInstructions(id);
    }

    private void removeCategoryFromInstructions(Long id) throws ServiceException {
        Category category = manager.findByName("In progress");
        List<Instruction> instructions = instructionManager.findAllByCategory(id);
        for (Instruction instruction: instructions) {
            instruction.setCategory(category);
            try {
                instructionManager.update(instruction);
            } catch (ManagerException e) {
                throw new ServiceException("Error in CategoryFacade.beforeDelete");
            }
        }
    }
}