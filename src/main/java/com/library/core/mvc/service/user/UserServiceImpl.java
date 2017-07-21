package com.library.core.mvc.service.user;

import com.library.core.config.security.model.JwtUserDetails;
import com.library.core.config.security.service.AuthenticationHelper;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.LoginException;
import com.library.dao.exceptions.ManagerException;
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

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Override
    public UserDTO findById(Long id) throws ManagerException {
        return userFacade.findById(id);
    }

    @Override
    public String login(UserDTO dto) throws ServiceException, LoginException {
        User user = userFacade.login(dto);
        JwtUserDetails details = new JwtUserDetails(user);
        return authenticationHelper.generateToken(details.getId());
    }

    @Override
    public UserDTO getMe() {
        return userFacade.getMe();
    }

    @Override
    public UserDTO update(UserDTO dto) throws ManagerException {
        return userFacade.update(dto);
    }
}
