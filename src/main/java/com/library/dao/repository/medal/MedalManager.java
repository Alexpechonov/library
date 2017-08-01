package com.library.dao.repository.medal;

import com.library.dao.model.entities.category.Category;
import com.library.dao.model.entities.medal.Medal;
import com.library.dao.repository.core.GenericManager;

/**
 * Created by user on 01.08.2017.
 */
public interface MedalManager extends GenericManager<Medal> {
    Medal findByName(String name);
}
