package com.library.core.mvc.controller.search;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.core.mvc.service.search.SearchService;
import com.library.dto.search.SearchDTO;
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

import java.util.List;

/**
 * Created by user on 04.08.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/open/search")
public class SearchController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService service;

    @RequestMapping(value = "/{data}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SearchDTO>> getInstructionRate(@PathVariable String data) {
        return new ResponseEntity<>(service.search(data), HttpStatus.OK);
    }
}
