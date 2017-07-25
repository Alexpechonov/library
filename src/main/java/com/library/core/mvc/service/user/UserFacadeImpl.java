package com.library.core.mvc.service.user;

import com.library.core.config.security.SecurityHelper;
import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.dao.exceptions.LoginException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.user.Role;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.instruction.InstructionManager;
import com.library.dao.repository.user.UserManager;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by user on 13.07.2017.
 */
@Service
@Transactional
public class UserFacadeImpl extends GenericFacadeImpl<UserManager, UserDTO, User> implements UserFacade {

    private static final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);

    @Autowired
    private UserManager userManager;

//    @Autowired
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    protected UserManager getManager() {
        return userManager;
    }

    @Override
    public User findByName(String name) {
        return userManager.findByUserName(name);
    }

    @Override
    public User convertToModel(UserDTO dto) {
        User user = new User();
        if (dto == null) {
            return user;
        }
        user.setId(dto.getId());
        user.setUserName(dto.getUserName());
        user.setIdentity(dto.getIdentity());
        user.setRole(dto.getRole());
        user.setEnabled(dto.getEnabled());
        user.setAbout(dto.getAbout());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setImage(dto.getImage());
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        if (user == null) {
            return dto;
        }
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setRole(user.getRole());
        dto.setEnabled(user.isEnabled());
        dto.setAbout(user.getAbout());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setImage(user.getImage());
        return dto;
    }

    @Override
    public UserDTO getMe() {
        Authentication authentication = SecurityHelper.getAuthenticationWithCheck();
        User byUsername = userManager.findByUserName(authentication.getName());

        return convertToDTO(byUsername);
    }

    @Override
    public UserDTO update(UserDTO dto) throws ManagerException {
        User user = convertToModel(dto);
        user.setId(getMe().getId());
        user.setUserName(getMe().getUserName());
        if(user.getIdentity() == null) {
            User currentUser = userManager.findById(user.getId());
            user.setIdentity(currentUser.getIdentity());
        }
        return convertToDTO(userManager.update(user));
    }

    @Override
    public User authExistUser(User user, String identity) throws LoginException{
        if (!encoder.matches(identity, user.getIdentity())) {
            throw new LoginException("Bad identity");
        }
        return user;
    }

    @Override
    public UserDTO insert(UserDTO dto) {
        try {
            dto.setIdentity(encoder.encode(dto.getIdentity()));
            dto.setRole(Role.ROLE_USER);
            dto.setEnabled(true);
            userManager.insert(convertToModel(dto));
        } catch (ManagerException e) {
            logger.error("error in UserManager.insert", e);
        }
        return dto;
    }
}
