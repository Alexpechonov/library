package com.library.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author i.katlinsky
 * @since 21.07.2016
 */
public class LoginResponseDto{
    private String token;

    public LoginResponseDto(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
