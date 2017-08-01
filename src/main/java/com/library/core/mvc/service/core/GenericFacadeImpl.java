package com.library.core.mvc.service.core;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.core.ModelObject;
import com.library.dao.repository.core.GenericManager;
import com.library.dto.core.AbstractDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public abstract class GenericFacadeImpl<MANAGER extends GenericManager<MODEL>, DTO extends AbstractDTO,
        MODEL extends ModelObject> implements GenericFacade<DTO, MODEL>{

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericFacadeImpl.class);

    protected abstract MANAGER getManager();

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public DTO findById(Long id) throws ManagerException {
        MODEL model = getManager().findById(id);
        return convertToDTO(model);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public List<DTO> findAll() {
        return convertToDTOList(getManager().findAll());
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public void remove(Long id) throws ServiceException {
        beforeDelete(id);
        try {
            getManager().remove(id);
        } catch (ManagerException e) {
            LOGGER.error("error in " + getManager().getClass().getSimpleName() + ".remove", e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public DTO update(DTO dto) throws ManagerException {
        return convertToDTO(getManager().update(convertToModel(dto)));
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public DTO insert(DTO dto) throws ServiceException {
        try {
            MODEL model = convertToModel(dto);
            beforeInsert(model);
            getManager().insert(model);
            afterInsert(model);
            return convertToDTO(model);
        } catch (ManagerException e) {
            LOGGER.error("error in " + getManager().getClass().getSimpleName() + ".insert", e);
            throw new ServiceException(e);
        }
    }

    public List<DTO> convertToDTOList(List<MODEL> modelList) {
        List<DTO> result = new ArrayList<>();
        for (MODEL model : modelList) {
            result.add(convertToDTO(model));
        }
        return result;
    }

    protected void beforeDelete(Long id) throws ServiceException {

    }

    protected void afterInsert(MODEL model) throws ServiceException {

    }

    protected void beforeInsert(MODEL model) throws ServiceException {

    }
}
