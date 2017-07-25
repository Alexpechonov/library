package com.library.core.mvc.controller.user;

import com.library.core.mvc.service.user.UserService;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.user.Role;
import com.library.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 13.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/open/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    protected UserService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        UserDTO dto;
        try {
            dto = service.findById(id);
        } catch (ManagerException e) {
            return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody UserDTO dto) {
        String token;
        try {
            token = service.login(dto);
        } catch (Exception e) {
            return new ResponseEntity<String>(new String(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}
