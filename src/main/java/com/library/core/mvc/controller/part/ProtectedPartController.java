package com.library.core.mvc.controller.part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 25.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/protected/part")
public class ProtectedPartController extends PartController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProtectedPartController.class);
}
