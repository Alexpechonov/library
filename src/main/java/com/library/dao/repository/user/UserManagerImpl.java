package com.library.dao.repository.user;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.user.QUser;
import com.library.dao.model.entities.user.User;
import com.mysema.query.jpa.impl.JPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by user on 13.07.2017.
 */
@Repository
public class UserManagerImpl implements UserManager{

    private final static Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void insert(User user) throws ManagerException {
        logger.info(User.class + ".insert()");
        if(user == null) {
            throw new ManagerException("model mustn't be equals null");
        }
        entityManager.persist(user);
    }

    @Override
    public User findById(Long id) throws ManagerException {
        if(id == null) {
            throw new ManagerException("id mustn't be equals null");
        }
        logger.info(UserManagerImpl.class + ".findByPK({})", id);
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUserName(String userName) {
        QUser user = QUser.user;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(user).where(user.userName.equalsIgnoreCase(userName));
        return query.singleResult(user);
    }

    @Override
    public User findByIdentity(String identity) {
        QUser user = QUser.user;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(user).where(user.identity.equalsIgnoreCase(identity));
        return query.singleResult(user);
    }
}
