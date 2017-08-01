package com.library.core.mvc.controller.medal;

import com.library.core.mvc.service.medal.MedalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 01.08.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/protected/medal")
public class ProtectedMedalController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProtectedMedalController.class);

    @Autowired
    private MedalService service;
}
