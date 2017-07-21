package com.library.core.mvc.controller.tag;

import com.library.core.mvc.service.tag.TagService;
import com.library.dto.tag.TagDTO;
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
 * Created by user on 21.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/tag")
public class TagController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagController.class);

    @Autowired
    protected TagService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TagDTO>> getAll() {
        return  new ResponseEntity<List<TagDTO>>(service.getAll(), HttpStatus.OK);
    }
}
