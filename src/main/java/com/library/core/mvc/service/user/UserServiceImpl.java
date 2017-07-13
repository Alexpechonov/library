package com.library.core.mvc.service.user;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.model.entities.user.User;
import com.library.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 13.07.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserFacade userFacade;

    @Override
    public User findByUserName(String username) {
        return userFacade.findByUserName(username);
    }

    @Override
    public void insert(UserDTO dto) throws ServiceException {
        userFacade.insert(dto);
    }
}
