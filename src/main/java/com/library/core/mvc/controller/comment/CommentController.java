package com.library.core.mvc.controller.comment;

import com.library.core.mvc.service.comment.CommentService;
import com.library.dto.comment.CommentDTO;
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
 * Created by user on 28.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/open/comment")
public class CommentController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    protected CommentService service;

    @RequestMapping(value = "/step/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDTO>> getAllByStep(@PathVariable Long id) {
        List<CommentDTO> comments = service.getAllByStep(id);
        return new ResponseEntity<List<CommentDTO>>(comments, HttpStatus.OK);
    }
}
