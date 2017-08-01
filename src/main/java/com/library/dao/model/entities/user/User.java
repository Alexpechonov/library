package com.library.dao.model.entities.user;

import com.library.dao.model.core.ModelObject;
import com.library.dao.model.entities.medal.Medal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by user on 13.07.2017.
 */
@Entity
@Table(name = "USERS")
public class User implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",
            sequenceName = "USER_SEQ",
            allocationSize = 1,
            initialValue = 100
    )
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false, length = 45)
    private String userName;

    @Column(name = "IDENTITY", unique = true, nullable = false, length = 60)
    private String identity;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "ABOUT", length = 500)
    private String about;

    @Column(name = "IMAGE", length = 1000)
    private String image;

    @ManyToMany()
    @JoinTable(name = "USER_MEDAL",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "MEDAL_ID")})
    private List<Medal> medals;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Medal> getMedals() {
        return medals;
    }

    public void setMedals(List<Medal> medals) {
        this.medals = medals;
    }
}
