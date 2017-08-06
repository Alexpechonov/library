package com.library.dao.repository.comment;

import com.library.dao.model.entities.comment.Comment;
import com.library.dao.model.entities.comment.QComment;
import com.library.dao.model.entities.instruction.QPart;
import com.library.dao.repository.core.GenericManagerImpl;
import com.library.dto.search.SearchDTO;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by user on 28.07.2017.
 */
@Repository
public class CommentManagerImpl extends GenericManagerImpl<Comment> implements CommentManager {

    @Override
    protected Class<Comment> getModelClass() {
        return Comment.class;
    }

    @Override
    public List<Comment> getAllByStep(Long stepId) {
        QComment comment = QComment.comment;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(comment).where(comment.step().id.eq(stepId));
        return query.list(comment);
    }

    @Override
    public List<Comment> getAllByUser(Long userId) {
        QComment comment = QComment.comment;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(comment).where(comment.user().id.eq(userId));
        return query.list(comment);
    }

    @Override
    public void deleteAllForUser(Long userId) {
        Query query = entityManager.createNativeQuery("DELETE FROM comments c WHERE c.user_id = " + userId);
        query.executeUpdate();
    }

    @Override
    public void deleteAllForStep(Long stepId) {
        Query query = entityManager.createNativeQuery("DELETE FROM comments c WHERE c.step = " + stepId);
        query.executeUpdate();
    }

    @Override
    public List<BigInteger> search(String string) {
        Query query = entityManager.createNativeQuery("SELECT id FROM comments WHERE " +
                "text @@ to_tsquery('" + string + "')");
        return query.getResultList();
    }

    @Override
    public BigInteger getInstructionId(Long id) {
        Query query = entityManager.createNativeQuery("SELECT instruction_id FROM steps WHERE " +
                "id = (SELECT c.step FROM comments c WHERE c.id = " + id + ")");
        return (BigInteger)query.getSingleResult();
    }

    @Override
    public String getText(Long id) {
        QComment comment = QComment.comment;
        JPAQuery query = new JPAQuery(entityManager);
        query.from(comment).where(comment.id.eq(id));
        return query.singleResult(comment.text);
    }
}
