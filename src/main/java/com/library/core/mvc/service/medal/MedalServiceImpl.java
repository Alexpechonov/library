package com.library.core.mvc.service.medal;

import com.library.core.mvc.service.core.GenericServiceImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dto.medal.MedalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 01.08.2017.
 */
@Service
public class MedalServiceImpl extends GenericServiceImpl<MedalFacade, MedalDTO> implements MedalService {

    @Autowired
    private MedalFacade facade;

    @Override
    protected MedalFacade getFacade() {
        return facade;
    }

    @Override
    public void addMedal(String name, Long userId) throws ServiceException {
        facade.addMedal(name, userId);
    }

    @Override
    public void deleteMedal(String name, Long userId) throws ServiceException {
        facade.deleteMedal(name, userId);
    }
}
