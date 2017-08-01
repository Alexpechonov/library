package com.library.core.mvc.controller.user;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by user on 22.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/protected/user")
public class ProtectedUserController extends UserController {

    private static final Logger logger = LoggerFactory.getLogger(ProtectedUserController.class);

    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getMe() {
        return new ResponseEntity<UserDTO>(service.getMe(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) {
        UserDTO user = null;
        try {
            user = service.update(dto);
        } catch (ManagerException e) {
            logger.error("error in UserService.update()");
        }
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        try {
            service.remove(id);
        } catch (ServiceException e) {
            logger.error("error in UserController.delete()");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> all = service.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
