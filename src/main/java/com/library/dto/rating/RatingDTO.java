package com.library.dto.rating;

import com.library.dto.core.AbstractDTO;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.user.UserDTO;

/**
 * Created by user on 01.08.2017.
 */
public class RatingDTO extends AbstractDTO {
    private Integer rate;
    private UserDTO user;
    private InstructionDTO instruction;

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public InstructionDTO getInstruction() {
        return instruction;
    }

    public void setInstruction(InstructionDTO instruction) {
        this.instruction = instruction;
    }
}
