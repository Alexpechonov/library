package com.library.core.mvc.controller.part;

import com.library.core.mvc.service.part.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 25.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/open/part")
public class PartController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PartController.class);

    @Autowired
    protected PartService service;
}
