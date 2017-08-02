package com.library.core.mvc.service.medal;

import com.library.core.mvc.service.core.GenericService;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dto.medal.MedalDTO;

/**
 * Created by user on 01.08.2017.
 */
public interface MedalService extends GenericService<MedalDTO> {

    void addMedal(String name, Long userId) throws ServiceException;

    void deleteMedal(String name, Long userId) throws ServiceException;
}
