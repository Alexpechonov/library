package com.library.core.mvc.service.user;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.model.entities.user.User;
import com.library.dto.user.UserDTO;

/**
 * Created by user on 13.07.2017.
 */
public interface UserService {
    User findByUserName(String username);

    void insert(UserDTO dto) throws ServiceException;
}
