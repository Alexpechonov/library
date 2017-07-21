package com.library.dto.instruction;

import com.library.dto.core.AbstractDTO;
import com.library.dto.tag.TagDTO;
import com.library.dto.user.UserDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public class InstructionDTO extends AbstractDTO {

    private String name;
    private List<StepDTO> steps;
    private List<TagDTO> tags;
    private UserDTO user;
    private Date creationDate;
    private Date lastModifiedDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<StepDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDTO> steps) {
        this.steps = steps;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
