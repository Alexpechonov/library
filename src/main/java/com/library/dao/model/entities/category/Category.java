package com.library.dao.model.entities.category;

import com.library.dao.model.core.ModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by user on 28.07.2017.
 */
@Entity
@Table(name = "CATEGORIES")
public class Category implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "CATEGORY_SEQ",
            allocationSize = 1,
            initialValue = 100
    )
    private Long id;

    @Column(name = "NAME", length = 45)
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
