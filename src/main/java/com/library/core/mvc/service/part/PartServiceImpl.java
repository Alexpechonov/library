package com.library.core.mvc.service.part;

import com.library.core.mvc.service.core.GenericServiceImpl;
import com.library.core.mvc.service.exception.AccessException;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.instruction.PartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 25.07.2017.
 */
@Service
public class PartServiceImpl extends GenericServiceImpl<PartFacade, PartDTO> implements PartService {

    @Autowired
    private PartFacade facade;

    @Override
    protected PartFacade getFacade() {
        return facade;
    }
}
