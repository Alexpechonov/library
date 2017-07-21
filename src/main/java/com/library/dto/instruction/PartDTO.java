package com.library.dto.instruction;

import com.library.dao.model.entities.instruction.PartType;
import com.library.dto.core.AbstractDTO;

/**
 * Created by user on 21.07.2017.
 */
public class PartDTO extends AbstractDTO {
    private PartType type;
    private String data;

    public PartType getType() {
        return type;
    }

    public void setType(PartType type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
