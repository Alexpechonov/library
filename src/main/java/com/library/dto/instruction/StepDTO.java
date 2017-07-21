package com.library.dto.instruction;

import com.library.dto.core.AbstractDTO;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public class StepDTO extends AbstractDTO {

    private List<PartDTO> parts;

    public List<PartDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartDTO> parts) {
        this.parts = parts;
    }
}
