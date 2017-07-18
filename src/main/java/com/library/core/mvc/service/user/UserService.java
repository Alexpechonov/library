package com.library.core.mvc.service.user;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.LoginException;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.user.UserDTO;

/**
 * Created by user on 13.07.2017.
 */
public interface UserService {
    UserDTO findById(Long id) throws ManagerException;

    String login(UserDTO dto) throws ServiceException, LoginException;

    UserDTO getMe();
}
