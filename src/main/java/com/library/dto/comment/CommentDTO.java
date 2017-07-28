package com.library.dto.comment;

import com.library.dao.model.entities.instruction.Step;
import com.library.dao.model.entities.user.User;
import com.library.dto.core.AbstractDTO;
import com.library.dto.instruction.StepDTO;
import com.library.dto.user.UserDTO;

import java.util.Date;

/**
 * Created by user on 28.07.2017.
 */
public class CommentDTO extends AbstractDTO {
    private UserDTO user;
    private StepDTO step;
    private String text;
    private Date creationDate;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public StepDTO getStep() {
        return step;
    }

    public void setStep(StepDTO step) {
        this.step = step;
    }
}
