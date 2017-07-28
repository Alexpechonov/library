package com.library.core.mvc.service.category;

import com.library.core.mvc.service.core.GenericServiceImpl;
import com.library.dto.category.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 28.07.2017.
 */
@Service
public class CategoryServiceImpl extends GenericServiceImpl<CategoryFacade, CategoryDTO> implements CategoryService {

    @Autowired
    private CategoryFacade facade;

    @Override
    protected CategoryFacade getFacade() {
        return facade;
    }
}
