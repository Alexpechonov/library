package com.library.dto.user;

/**
 * @author i.katlinsky
 * @since 21.07.2016
 */
public class LoginRequestDto{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
