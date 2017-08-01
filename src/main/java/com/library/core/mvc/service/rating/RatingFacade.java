package com.library.core.mvc.service.rating;

import com.library.core.mvc.service.core.GenericFacade;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.model.entities.rating.Rating;
import com.library.dto.rating.RatingDTO;

import java.util.List;

/**
 * Created by user on 01.08.2017.
 */
public interface RatingFacade extends GenericFacade<RatingDTO, Rating> {
    RatingDTO findByUserAndInstruction(Long userId, Long instructionId);

    List<RatingDTO> findAllByInstruction(Long instructionId) throws ServiceException;
}
