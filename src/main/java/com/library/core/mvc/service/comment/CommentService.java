package com.library.core.mvc.service.comment;

import com.library.core.mvc.service.core.GenericService;
import com.library.dto.comment.CommentDTO;

import java.util.List;

/**
 * Created by user on 28.07.2017.
 */
public interface CommentService extends GenericService<CommentDTO> {
    List<CommentDTO> getAllByStep(Long stepId);
}
