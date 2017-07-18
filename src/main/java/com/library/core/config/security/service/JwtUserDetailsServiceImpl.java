package com.library.core.config.security.service;

import com.library.core.config.security.exception.JsonException;
import com.library.core.config.security.model.JwtUserDetails;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserManager userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = this.userRepository.findByUserName(username);

        return Optional.ofNullable(byUsername)
                .map(JwtUserDetails::new)
                .orElseThrow(() -> new JsonException("User nor found."));
    }
}
