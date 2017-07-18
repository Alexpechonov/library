package com.library.core.mvc.service.user;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.LoginException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.user.User;
import com.library.dto.user.UserDTO;

/**
 * Created by user on 13.07.2017.
 */
public interface UserFacade {
    User findByName(String username);

    UserDTO findById(Long id) throws ManagerException;

    User convertToModel(UserDTO dto);

    UserDTO convertToDTO(User user);

    User login(UserDTO dto) throws LoginException;

    UserDTO getMe();
}
