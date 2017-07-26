package com.library.core.mvc.service.part;

import com.library.core.mvc.service.core.GenericFacade;
import com.library.core.mvc.service.exception.AccessException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Part;
import com.library.dto.instruction.PartDTO;

import java.util.List;

/**
 * Created by user on 25.07.2017.
 */
public interface PartFacade extends GenericFacade<PartDTO, Part> {
}
