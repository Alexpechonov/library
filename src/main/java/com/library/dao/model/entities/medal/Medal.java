package com.library.dao.model.entities.medal;

import com.library.dao.model.core.ModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by user on 01.08.2017.
 */
@Entity
@Table(name = "MEDALS")
public class Medal implements ModelObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medal_seq")
    @SequenceGenerator(name = "medal_seq",
            sequenceName = "MEDAL_SEQ",
            allocationSize = 1,
            initialValue = 100
    )
    private Long id;

    @Column(name = "NAME", nullable = false, length = 45)
    private String name;

    @Column(name = "IMAGE", nullable = false)
    private String image;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
