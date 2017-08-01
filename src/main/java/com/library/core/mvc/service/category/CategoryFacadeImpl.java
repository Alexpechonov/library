package com.library.core.mvc.service.category;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.model.entities.category.Category;
import com.library.dao.repository.category.CategoryManager;
import com.library.dto.category.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        if(manager.findByName(dto.getName()).getName().equals(dto.getName())) {
            return null;
        }
        return super.insert(dto);
    }
}
