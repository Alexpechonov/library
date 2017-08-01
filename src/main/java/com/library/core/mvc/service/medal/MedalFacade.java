package com.library.core.mvc.service.medal;

import com.library.core.mvc.service.core.GenericFacade;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.model.entities.medal.Medal;
import com.library.dto.medal.MedalDTO;

import java.util.List;

/**
 * Created by user on 01.08.2017.
 */
public interface MedalFacade extends GenericFacade<MedalDTO, Medal> {
    void checkInstructions() throws ServiceException;

    void checkComments() throws ServiceException;

    List<Medal> convertToModelList(List<MedalDTO> dtos);
}
