package com.library.dao.repository.tag;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.tag.QTag;
import com.library.dao.model.entities.tag.Tag;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.core.GenericManagerImpl;
import com.library.dto.search.SearchDTO;
import com.mysema.query.jpa.impl.JPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
@Repository
public class TagManagerImpl extends GenericManagerImpl<Tag> implements TagManager {

    private final static Logger logger = LoggerFactory.getLogger(TagManagerImpl.class);

    @PersistenceContext
    private EntityManager manager;

    @Override
    protected Class<Tag> getModelClass() { return Tag.class; }

    @Override
    public Tag findByName(String name) {
        QTag tag = QTag.tag;
        JPAQuery query = new JPAQuery(manager);
        query.from(tag).where(tag.name.equalsIgnoreCase(name));
        return query.singleResult(tag);
    }
}
