package com.library.core.config.security;

import com.library.core.config.security.exception.JsonException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
public class SecurityHelper {

    public static Authentication getAuthenticationWithCheck() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean checkAuthenticationExists = authentication != null && authentication.isAuthenticated();
        if (checkAuthenticationExists) {
            return authentication;
        }

        throw new JsonException("Authentication failed.");
    }
}