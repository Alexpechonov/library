package com.library.core.config.JWT;

/**
 * Created by user on 13.07.2017.
 */
public class JwtPayload {
    private Long id;
    private Boolean isAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
