package com.library.dto.user;

import com.library.dao.model.entities.user.Role;
import com.library.dto.core.AbstractDTO;
import com.library.dto.medal.MedalDTO;

import java.util.List;

/**
 * Created by user on 13.07.2017.
 */
public class UserDTO extends AbstractDTO {

    private String userName;
    private String identity;
    private String image;
    private Boolean enabled;
    private Role role;
    private String firstName;
    private String lastName;
    private String about;
    private List<MedalDTO> medals;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public List<MedalDTO> getMedals() {
        return medals;
    }

    public void setMedals(List<MedalDTO> medals) {
        this.medals = medals;
    }
}
