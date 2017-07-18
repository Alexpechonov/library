package com.library.core.config.security.service;

import com.library.core.config.security.exception.ExpiredTokenAuthenticationException;
import com.library.core.config.security.exception.InvalidTokenAuthenticationException;
import com.library.core.config.security.model.JwtAuthenticationToken;
import com.library.core.config.security.model.JwtUserDetails;
import com.library.core.config.security.model.TokenPayload;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final long MILLIS_IN_SECOND = 1000L;

    @Autowired
    private UserManager userRepository;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Override
    public Authentication authenticate(final Authentication authRequest) {
        // Getting string token from authentication request object
        String token = StringUtils.trimToNull((String) authRequest.getCredentials());

        //  Deserialize token
        TokenPayload tokenPayload = authenticationHelper.decodeToken(token);

        // Checking if token already expired and throwing an AuthenticationException in this case
        checkIsExpired(tokenPayload.getExp());

        // Getting user id from token
        Long userEntityId = tokenPayload.getUserId();
        if (Objects.isNull(userEntityId)) {
            throw new InvalidTokenAuthenticationException("Token does not contain a user id.");
        }

        // Getting user from database
        User user = null;
        try {
            user = userRepository.findById(userEntityId);
        } catch (ManagerException e) {}

        if (Objects.isNull(user)) {
            throw new InvalidTokenAuthenticationException("Token does not contain existed user id.");
        }

        // Return authenticated Authentication
        JwtUserDetails userDetails = new JwtUserDetails(user);
        return new JwtAuthenticationToken(userDetails);
    }

    private void checkIsExpired(final Long tokenExpirationTime) {
        if ((System.currentTimeMillis() / MILLIS_IN_SECOND) > tokenExpirationTime) {
            throw new ExpiredTokenAuthenticationException();
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
