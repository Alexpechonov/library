package com.library.core.mvc.service.user;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.user.UserManager;
import com.library.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User findByUserName(String username) {
        return userManager.findByUserName(username);
    }

    @Override
    public void insert(UserDTO dto) throws ServiceException {
        User user = convertToModel(dto);
        User userByName = findByUserName(user.getUsername());
        if (userByName != null) {
            throw new ServiceException("user with name '" + user.getUsername() + "' already exist");
        }
        try {
            userManager.insert(user);
        } catch (ManagerException e) {
            logger.error("error in UserManager.insert", e);
        }
    }

    @Override
    public User convertToModel(UserDTO dto) {
        User user = new User();
        if (dto == null) {
            return user;
        }
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        user.setEnabled(dto.getEnabled());
        user.setAbout(dto.getAbout());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        if (user == null) {
            return dto;
        }
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setEnabled(user.isEnabled());
        dto.setAbout(user.getAbout());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
