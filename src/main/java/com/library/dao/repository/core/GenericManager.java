package com.library.dao.repository.core;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.core.ModelObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public interface GenericManager<MODEL extends ModelObject> extends Serializable {

    MODEL findById(Long id) throws ManagerException;

    List<MODEL> findAll();

    void insert(MODEL model) throws ManagerException;

    MODEL update(MODEL model) throws ManagerException;

    void remove(Long id) throws ManagerException;
}
