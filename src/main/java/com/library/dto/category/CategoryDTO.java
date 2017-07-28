package com.library.dto.category;

import com.library.dto.core.AbstractDTO;

/**
 * Created by user on 28.07.2017.
 */
public class CategoryDTO extends AbstractDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
