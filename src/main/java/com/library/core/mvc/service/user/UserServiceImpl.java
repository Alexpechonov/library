package com.library.core.mvc.service.user;

import com.library.core.config.security.model.JwtUserDetails;
import com.library.core.config.security.service.AuthenticationHelper;
import com.library.core.mvc.service.core.GenericServiceImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.core.mvc.service.instruction.InstructionFacade;
import com.library.dao.exceptions.LoginException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.user.User;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 13.07.2017.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<UserFacade, UserDTO> implements UserService {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Override
    protected UserFacade getFacade() {
        return userFacade;
    }

    @Override
    public String login(UserDTO dto) throws ServiceException, LoginException {
        User user;
        User userByIdentity = userFacade.findByName(dto.getUserName());
        if (userByIdentity != null) {
            user = userFacade.authExistUser(userByIdentity, dto.getIdentity());
        } else {
            dto = userFacade.insert(dto);
            dto.setId(userFacade.findByName(dto.getUserName()).getId());
            user = userFacade.convertToModel(dto);
        }
        JwtUserDetails details = new JwtUserDetails(user);
        return authenticationHelper.generateToken(details.getId(), details.getRole());
    }

    @Override
    public UserDTO getMe() {
        return userFacade.getMe();
    }

}
