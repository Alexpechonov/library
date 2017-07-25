package com.library.dao.repository.core;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.core.ModelObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public abstract class GenericManagerImpl<MODEL extends ModelObject> implements GenericManager<MODEL> {

    private static final Logger logger = LoggerFactory.getLogger(GenericManagerImpl.class);

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<MODEL> getModelClass();

    @Override
    public MODEL findById(Long id) throws ManagerException {
        if (id == null) {
            throw new ManagerException("id mustn't be equals null");
        }
        logger.info(getModelClass().getSimpleName() + ".findById({})", id);
        return entityManager.find(getModelClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MODEL> findAll() {
        logger.info(getModelClass().getSimpleName() + ".findAll()");
        return entityManager.createQuery("select t from " + getModelClass().getSimpleName() + " t").getResultList();
    }

    @Override
    public void insert(MODEL model) throws ManagerException {
        logger.info(getModelClass().getSimpleName() + ".insert()");
        if (model == null) {
            throw new ManagerException("model mustn't be equals null");
        }
        entityManager.persist(model);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public MODEL update(MODEL model) throws ManagerException {
        if (model == null) {
            throw new ManagerException("model mustn't be equals null");
        }
        logger.info(getModelClass().getSimpleName() + ".update() id={}", model.getId());
        MODEL merge = entityManager.merge(model);
        entityManager.flush();
        return merge;
    }

    @Override
    public void remove(Long id) throws ManagerException {
        if (id == null || id == 0) {
            throw new ManagerException("id mustn't be equals null");
        }
        logger.info(getModelClass().getSimpleName() + ".remove() id={}", id);
        entityManager.remove(findById(id));
    }

}
