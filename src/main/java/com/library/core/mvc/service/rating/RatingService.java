package com.library.core.mvc.service.rating;

import com.library.core.mvc.service.core.GenericService;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dto.rating.RatingDTO;

/**
 * Created by user on 01.08.2017.
 */
public interface RatingService extends GenericService<RatingDTO> {
    Integer findMyRateByInstruction(Long instructionId) throws ServiceException;

    Float getRatingOfInstruction(Long instructionId) throws ServiceException;
}
