package com.library.dto.instruction;

import com.library.dto.core.AbstractDTO;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public class StepDTO extends AbstractDTO {

    private String name;
    private List<PartDTO> parts;

    public List<PartDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartDTO> parts) {
        this.parts = parts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
