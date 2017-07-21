package com.library.dao.model.entities.instruction;


import com.library.dao.model.core.ModelObject;
import com.library.dao.model.entities.tag.Tag;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 21.07.2017.
 */
@Entity
@Table(name = "INSTRUCTIONS")
public class Instruction implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instruction_seq")
    @SequenceGenerator(name = "instruction_seq",
            sequenceName = "INSTRUCTION_SEQ",
            allocationSize = 1,
            initialValue = 100
    )
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false, length = 45)
    private String name;

    @JoinColumn(name = "STEPS")
    @OneToMany(targetEntity = Step.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Step> steps;

    @ManyToMany(targetEntity = Tag.class)
    @JoinTable(name = "INSTRUCTION_TAG",
            joinColumns = @JoinColumn(name = "INSTRUCTION_ID"),
            inverseJoinColumns = @JoinColumn(name ="TAG_ID"))
    private Set<Tag> tags;

    @Column(name = "CREATION_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

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

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
