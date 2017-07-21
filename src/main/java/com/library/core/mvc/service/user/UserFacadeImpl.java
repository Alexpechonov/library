package com.library.core.mvc.service.user;

import com.library.core.config.security.SecurityHelper;
import com.library.dao.exceptions.LoginException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.user.UserManager;
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
public class UserFacadeImpl implements UserFacade {

    private static final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);

    @Autowired
    private UserManager userManager;

//    @Autowired
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User findByName(String name) {
        return userManager.findByUserName(name);
    }

    @Override
    public UserDTO findById(Long id) throws ManagerException {
        User user = userManager.findById(id);
        return convertToDTO(user);
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
    public User login(UserDTO dto) throws LoginException{
        User user = convertToModel(dto);
        User userByIdentity = findByName(user.getUserName());
        if (userByIdentity != null) {
            return authExistUser(userByIdentity, user);
        }
        return insert(user);
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

    private User authExistUser(User user, User newUser) throws LoginException{
        if (!encoder.matches(newUser.getIdentity(), user.getIdentity())) {
            throw new LoginException("Bad identity");
        }
        return user;
    }

    private User insert(User user) {
        try {
            user.setIdentity(encoder.encode(user.getIdentity()));
            userManager.insert(user);
        } catch (ManagerException e) {
            logger.error("error in UserManager.insert", e);
        }
        return user;
    }
}
