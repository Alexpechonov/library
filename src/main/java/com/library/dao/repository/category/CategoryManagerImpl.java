package com.library.dao.repository.category;

import com.library.dao.model.entities.category.Category;
import com.library.dao.model.entities.category.QCategory;
import com.library.dao.repository.core.GenericManagerImpl;
import com.mysema.query.jpa.impl.JPAQuery;
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

    @Override
    public Category findByName(String name) {
        QCategory category = QCategory.category;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(category).where(category.name.equalsIgnoreCase(name));
        return query.singleResult(category);
    }
}
