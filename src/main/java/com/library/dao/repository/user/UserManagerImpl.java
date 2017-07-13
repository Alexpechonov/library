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
    public User findByUserName(String username) {
        QUser user = QUser.user;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(user).where(user.username.equalsIgnoreCase(username));
        return query.singleResult(user);
    }
}
