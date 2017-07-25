package com.library.dao.repository.part;

import com.library.dao.model.entities.instruction.Part;
import com.library.dao.repository.core.GenericManagerImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 25.07.2017.
 */
@Repository
public class PartManagerImpl extends GenericManagerImpl<Part> implements PartManager {
    @Override
    protected Class<Part> getModelClass() {
        return Part.class;
    }
}
