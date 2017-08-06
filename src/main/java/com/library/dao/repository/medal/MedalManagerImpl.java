package com.library.dao.repository.medal;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.medal.Medal;
import com.library.dao.model.entities.medal.QMedal;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.core.GenericManagerImpl;
import com.library.dto.search.SearchDTO;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 01.08.2017.
 */
@Repository
public class MedalManagerImpl extends GenericManagerImpl<Medal> implements MedalManager {
    @Override
    protected Class<Medal> getModelClass() {
        return Medal.class;
    }

    @Override
    public Medal findByName(String name) {
        QMedal medal = QMedal.medal;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(medal).where(medal.name.equalsIgnoreCase(name));
        return query.singleResult(medal);
    }
}
