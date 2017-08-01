package com.library.dao.repository.rating;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.rating.Rating;
import com.library.dao.repository.core.GenericManager;

import java.util.List;

/**
 * Created by user on 01.08.2017.
 */
public interface RatingManager extends GenericManager<Rating> {
    Rating getByUserAndInstruction(Long userId, Long instructionId) throws ManagerException;

    List<Rating> findAllByInstruction(Long instructionId) throws ManagerException;

    public void deleteAllForUser(Long userId);

    public void deleteAllForInstruction(Long instructionId);
}
