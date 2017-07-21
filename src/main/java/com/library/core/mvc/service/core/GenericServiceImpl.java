package com.library.core.mvc.service.core;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.core.AbstractDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public abstract class GenericServiceImpl<FACADE extends GenericFacade, DTO extends AbstractDTO>
        implements GenericService<DTO> {

    private static final Logger logger = LoggerFactory.getLogger(GenericServiceImpl.class);

    protected abstract FACADE getFacade();

    @Override
    @SuppressWarnings("unchecked")
    public DTO findById(Long id) throws ManagerException {
        return (DTO) getFacade().findById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DTO> findAll() {
        return getFacade().findAll();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(Long id) throws ServiceException {
        getFacade().remove(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public DTO insert(DTO dto) throws ServiceException {
        beforeInsert(dto);
        return (DTO) getFacade().insert(dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    public DTO update(DTO dto) throws ManagerException {
        return (DTO) getFacade().update(dto);
    }

    protected void beforeInsert(DTO dto) {

    }

}
