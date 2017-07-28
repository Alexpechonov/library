package com.library.core.mvc.service.comment;

import com.library.core.mvc.service.core.GenericServiceImpl;
import com.library.dto.comment.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 28.07.2017.
 */
@Service
public class CommentServiceImpl extends GenericServiceImpl<CommentFacade, CommentDTO> implements CommentService {

    @Autowired
    private CommentFacade facade;

    @Override
    protected CommentFacade getFacade() {
        return facade;
    }
}
