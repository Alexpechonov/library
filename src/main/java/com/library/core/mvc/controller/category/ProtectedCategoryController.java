package com.library.core.mvc.controller.category;

import com.library.core.mvc.controller.instruction.ProtectedInstructionController;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.category.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 28.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/protected/category")
public class ProtectedCategoryController extends CategoryController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProtectedInstructionController.class);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
        CategoryDTO result = null;
        try {
            result = service.insert(dto);
        } catch (ServiceException e) {
            LOGGER.error("error in ProtectedCategoryController.create");
        }
        return new ResponseEntity<CategoryDTO>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO dto) {
        CategoryDTO category = null;
        try {
            category = service.update(dto);
        } catch (ManagerException e) {
            LOGGER.error("error in CategoryController.update()");
        }
        return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        try {
            service.remove(id);
        } catch (ServiceException e) {
            LOGGER.error("error in CategoryController.remove()");
        }
    }

}
