package com.library.core.mvc.controller.rating;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.core.mvc.service.rating.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 01.08.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/open/rating")
public class RatingController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    protected RatingService service;

    @RequestMapping(value = "/instruction/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Float> getInstructionRate(@PathVariable Long id) {
        Float rate;
        try {
            rate = service.getRatingOfInstruction(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(new Float(0), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }
}
