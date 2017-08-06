package com.library.core.mvc.controller.medal;

import com.library.core.mvc.service.medal.MedalService;
import com.library.dto.medal.MedalDTO;
import com.library.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by user on 02.08.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/open/medal")
public class MedalController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MedalController.class);

    @Autowired
    protected MedalService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedalDTO>> findAll() {
        List<MedalDTO> all = service.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
