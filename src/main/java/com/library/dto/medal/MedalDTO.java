package com.library.dto.medal;

import com.library.dto.core.AbstractDTO;

/**
 * Created by user on 01.08.2017.
 */
public class MedalDTO extends AbstractDTO {
    private String name;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
