package com.library.dto.user;

import com.library.dao.model.entities.user.Role;
import com.library.dto.core.AbstractDTO;

/**
 * Created by user on 13.07.2017.
 */
public class UserDTO extends AbstractDTO {

    private String username;
    private Boolean enabled;
    private Role role;
    private String firstName;
    private String lastName;
    private String about;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
