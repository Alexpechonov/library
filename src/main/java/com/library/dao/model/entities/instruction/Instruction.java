package com.library.dao.model.entities.instruction;


import com.library.dao.model.core.ModelObject;
import com.library.dao.model.entities.category.Category;
import com.library.dao.model.entities.tag.Tag;
import com.library.dao.model.entities.user.User;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

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

    @Column(name = "NAME", length = 45)
    private String name;

    @JoinColumn(name = "INSTRUCTION_ID")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Step> steps;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "INSTRUCTION_TAG",
            joinColumns = @JoinColumn(name = "INSTRUCTION_ID"),
            inverseJoinColumns = @JoinColumn(name ="TAG_ID"))
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

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

    public List<Tag> getTags() { return tags; }

    public void setTags(List<Tag> tags) { this.tags = tags; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
