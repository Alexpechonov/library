package com.library.core.mvc.service.comment;

import com.library.core.mvc.service.core.GenericFacade;
import com.library.dao.model.entities.comment.Comment;
import com.library.dto.comment.CommentDTO;

import java.util.List;

/**
 * Created by user on 28.07.2017.
 */
public interface CommentFacade extends GenericFacade<CommentDTO, Comment> {

    List<CommentDTO> getAllByStep(Long stepId);
}
