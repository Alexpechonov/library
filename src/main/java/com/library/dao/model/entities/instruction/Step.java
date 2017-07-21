package com.library.dao.model.entities.instruction;

import com.library.dao.model.core.ModelObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
@Entity
@Table(name = "STEPS")
public class Step implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "step_seq")
    @SequenceGenerator(name = "step_seq",
            sequenceName = "STEP_SEQ",
            allocationSize = 1,
            initialValue = 100
    )
    private Long id;

    @JoinColumn(name = "PARTS")
    @OneToMany(targetEntity = Part.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Part> parts;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
