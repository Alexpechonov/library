package com.library.core.mvc.service.medal;

import com.library.core.mvc.service.core.GenericServiceImpl;
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
}
