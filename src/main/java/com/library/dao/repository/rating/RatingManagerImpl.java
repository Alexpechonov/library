package com.library.dao.repository.rating;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.QInstruction;
import com.library.dao.model.entities.rating.QRating;
import com.library.dao.model.entities.rating.Rating;
import com.library.dao.repository.core.GenericManagerImpl;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by user on 01.08.2017.
 */
@Repository
public class RatingManagerImpl extends GenericManagerImpl<Rating> implements RatingManager {
    @Override
    protected Class<Rating> getModelClass() {
        return Rating.class;
    }

    @Override
    public Rating getByUserAndInstruction(Long userId, Long instructionId) throws ManagerException {
        if (instructionId == null) {
            throw new ManagerException("id mustn't be equals null");
        }
        QRating rating = QRating.rating;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(rating).where(rating.user().id.eq(userId).and(rating.instruction().id.eq(instructionId)));
        return query.singleResult(rating);
    }

    @Override
    public List<Rating> findAllByInstruction(Long instructionId) throws ManagerException {
        QRating rating = QRating.rating;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(rating).where(rating.instruction().id.eq(instructionId));
        return query.list(rating);
    }

    public void deleteAllForUser(Long userId) {
        Query query = entityManager.createNativeQuery("DELETE FROM ratings c WHERE c.user_id = " + userId);
        query.executeUpdate();
    }

    @Override
    public void deleteAllForInstruction(Long instructionId) {
        Query query = entityManager.createNativeQuery("DELETE FROM ratings c WHERE c.instruction_id = " + instructionId);
        query.executeUpdate();
    }
}
