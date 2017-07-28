package com.library.dao.repository.category;

import com.library.dao.model.entities.category.Category;
import com.library.dao.repository.core.GenericManagerImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 28.07.2017.
 */
@Repository
public class CategoryManagerImpl extends GenericManagerImpl<Category> implements CategoryManager {
    @Override
    protected Class<Category> getModelClass() {
        return Category.class;
    }
}
