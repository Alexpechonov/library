package com.library.dto.tag;

import com.library.dto.core.AbstractDTO;

/**
 * Created by user on 21.07.2017.
 */
public class TagDTO extends AbstractDTO {

    public TagDTO(){}

    public TagDTO(String name) {
        this.name = name;
    }
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
