package com.library.dao.repository.comment;

import com.library.dao.model.entities.comment.Comment;
import com.library.dao.repository.core.GenericManager;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by user on 28.07.2017.
 */
public interface CommentManager extends GenericManager<Comment> {
    List<Comment> getAllByStep(Long stepId);

    List<Comment> getAllByUser(Long userId);

    void deleteAllForUser(Long userId);

    void deleteAllForStep(Long stepId);

    List<BigInteger> search(String string);

    BigInteger getInstructionId(Long id);

    String getText(Long id);
}
