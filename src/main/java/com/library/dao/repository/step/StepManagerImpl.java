package com.library.dao.repository.step;

import com.library.dao.model.entities.instruction.Step;
import com.library.dao.repository.core.GenericManagerImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 25.07.2017.
 */
@Repository
public class StepManagerImpl extends GenericManagerImpl<Step> implements StepManager {
    @Override
    protected Class<Step> getModelClass() {
        return Step.class;
    }
}
