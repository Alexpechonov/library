package com.library.dao.repository.comment;

import com.library.dao.model.entities.comment.Comment;
import com.library.dao.repository.core.GenericManager;

import java.util.List;

/**
 * Created by user on 28.07.2017.
 */
public interface CommentManager extends GenericManager<Comment> {
    List<Comment> getAllByStep(Long stepId);
}
