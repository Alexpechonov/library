package com.library.dao.model.entities.tag;

import com.library.dao.model.core.ModelObject;
import com.library.dao.model.entities.instruction.Instruction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by user on 21.07.2017.
 */
@Entity
@Table(name = "TAGS")
public class Tag implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_seq")
    @SequenceGenerator(name = "tag_seq",
            sequenceName = "TAG_SEQ",
            allocationSize = 1,
            initialValue = 100
    )
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false, length = 45)
    private String name;

    @ManyToMany(targetEntity = Instruction.class)
    @JoinTable(name = "INSTRUCTION_TAG",
            joinColumns = @JoinColumn(name = "TAG_ID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUCTION_ID"))
    private Set<Instruction> instructions;

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

    public Set<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(Set<Instruction> instructions) {
        this.instructions = instructions;
    }
}
