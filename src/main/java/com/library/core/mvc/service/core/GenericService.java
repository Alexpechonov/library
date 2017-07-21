package com.library.core.mvc.service.core;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.core.AbstractDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public interface GenericService<DTO extends AbstractDTO> extends Serializable {

    DTO findById(Long id) throws ManagerException;

    List<DTO> findAll();

    void remove(Long id) throws ServiceException;

    DTO update(DTO dto) throws ManagerException;

    DTO insert(DTO dto) throws ServiceException;
}
