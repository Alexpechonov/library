package com.library.core.mvc.controller.user;

import com.library.core.mvc.service.user.UserService;
import com.library.dao.model.entities.user.User;
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
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    protected UserService service;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody UserDTO dto) {
        dto.setEnabled(true);
        service.insert(dto);
    }

    @RequestMapping(value = "/check/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        User user = service.findByUserName(username);
        return new ResponseEntity<Boolean>(user == null, HttpStatus.OK);
    }
}
