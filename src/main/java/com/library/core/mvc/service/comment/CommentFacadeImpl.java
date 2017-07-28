package com.library.core.mvc.service.comment;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.model.entities.comment.Comment;
import com.library.dao.model.entities.instruction.Step;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.comment.CommentManager;
import com.library.dto.comment.CommentDTO;
import com.library.dto.instruction.StepDTO;
import com.library.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by user on 28.07.2017.
 */
@Service
@Transactional
public class CommentFacadeImpl extends GenericFacadeImpl<CommentManager, CommentDTO, Comment> implements CommentFacade{

    @Autowired
    private CommentManager manager;

    @Override
    protected CommentManager getManager() {
        return manager;
    }

    @Override
    public Comment convertToModel(CommentDTO dto) {
        Comment result = new Comment();
        result.setId(dto.getId());
        result.setCreationDate(dto.getCreationDate());
        result.setText(dto.getText());
        User user = new User();
        user.setId(dto.getUser().getId());
        user.setFirstName(dto.getUser().getFirstName());
        user.setLastName(dto.getUser().getLastName());
        user.setImage(dto.getUser().getImage());
        user.setRole(dto.getUser().getRole());
        Step step = new Step();
        step.setId(dto.getStep().getId());
        result.setUser(user);
        result.setStep(step);
        return result;
    }

    @Override
    public CommentDTO convertToDTO(Comment comment) {
        CommentDTO result = new CommentDTO();
        result.setId(comment.getId());
        result.setCreationDate(comment.getCreationDate());
        result.setText(comment.getText());
        UserDTO user = new UserDTO();
        user.setId(comment.getUser().getId());
        user.setFirstName(comment.getUser().getFirstName());
        user.setLastName(comment.getUser().getLastName());
        user.setRole(comment.getUser().getRole());
        user.setImage(comment.getUser().getImage());
        StepDTO step = new StepDTO();
        step.setId(comment.getStep().getId());
        result.setUser(user);
        result.setStep(step);
        return result;
    }

    @Override
    public CommentDTO insert(CommentDTO dto) throws ServiceException {
        dto.setCreationDate(new Date());
        return super.insert(dto);
    }
}
