package com.library.core.mvc.service.user;

import com.library.core.mvc.service.core.GenericFacade;
import com.library.dao.exceptions.LoginException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.user.User;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.user.UserDTO;

/**
 * Created by user on 13.07.2017.
 */
public interface UserFacade extends GenericFacade<UserDTO, User> {

    User findByName(String username);

    User login(UserDTO dto) throws LoginException;

    UserDTO getMe();
}
