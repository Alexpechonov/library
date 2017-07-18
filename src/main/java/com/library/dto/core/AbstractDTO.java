package com.library.dto.core;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by user on 13.07.2017.
 */
public abstract class AbstractDTO {

    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
