package com.library.core.config.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author i.katlinsky
 * @since 21.07.2016
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
    }
}
