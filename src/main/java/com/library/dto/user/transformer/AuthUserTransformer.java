package com.library.dto.user.transformer;

import com.library.dao.model.entities.user.User;
import com.library.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author i.katlinsky
 * @since 22.07.2016
 */
@Component
@RequiredArgsConstructor
public class AuthUserTransformer {

    public UserDTO makeDto(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setRole(user.getRole());

        return dto;
    }

}
