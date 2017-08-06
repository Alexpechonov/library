package com.library.core.mvc.controller.category;

import com.library.core.mvc.service.category.CategoryService;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.category.CategoryDTO;
import com.library.dto.instruction.InstructionDTO;
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
@RequestMapping("api/open/category")
public class CategoryController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    protected CategoryService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        CategoryDTO dto;
        try {
            dto = service.findById(id);
        } catch (ManagerException e) {
            return new ResponseEntity<>(new CategoryDTO(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
