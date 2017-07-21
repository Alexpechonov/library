package com.library.dao.repository.user;

import com.library.dao.model.entities.user.QUser;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.core.GenericManagerImpl;
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
public class UserManagerImpl extends GenericManagerImpl<User> implements UserManager{

    private final static Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected Class<User> getModelClass() {
        return User.class;
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
