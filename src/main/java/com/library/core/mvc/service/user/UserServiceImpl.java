package com.library.core.mvc.service.user;

import com.library.core.config.JWT.JwtEncoder;
import com.library.core.config.JWT.JwtPayload;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.user.Role;
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
    public UserDTO findById(Long id) throws ManagerException {
        return userFacade.findById(id);
    }

    @Override
    public String login(UserDTO dto) throws ServiceException {
        dto = userFacade.login(dto);
        JwtPayload payload = new JwtPayload();
        payload.setId(dto.getId());
        payload.setAdmin(dto.getRole().equals(Role.ROLE_ADMIN));
        return JwtEncoder.createJwt(payload);
    }
}
