package com.library.dao.repository.comment;

import com.library.dao.model.entities.comment.Comment;
import com.library.dao.repository.core.GenericManagerImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 28.07.2017.
 */
@Repository
public class CommentManagerImpl extends GenericManagerImpl<Comment> implements CommentManager {

    @Override
    protected Class<Comment> getModelClass() {
        return Comment.class;
    }
}
