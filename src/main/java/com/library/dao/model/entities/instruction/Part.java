package com.library.dao.model.entities.instruction;

import com.library.dao.model.core.ModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by user on 21.07.2017.
 */
@Entity
@Table(name = "PARTS")
public class Part implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_seq")
    @SequenceGenerator(name = "part_seq",
            sequenceName = "PART_SEQ",
            allocationSize = 1,
            initialValue = 100
    )
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "PART_TYPE", nullable = false)
    private PartType type;

    @Column(name = "DATA", length = 500)
    private String data;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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
