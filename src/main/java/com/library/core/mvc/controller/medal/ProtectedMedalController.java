package com.library.core.mvc.controller.medal;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.core.mvc.service.medal.MedalService;
import com.library.dto.category.CategoryDTO;
import com.library.dto.medal.MedalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by user on 01.08.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/protected/medal")
public class ProtectedMedalController extends MedalController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProtectedMedalController.class);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody MedalDTO dto, @PathVariable Long id) {
        try {
            service.addMedal(dto.getName(), id);
        } catch (ServiceException e) {
            LOGGER.error("error in ProtectedCategoryController.create");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody MedalDTO dto, @PathVariable Long id) {
        try {
            service.deleteMedal(dto.getName(), id);
        } catch (ServiceException e) {
            LOGGER.error("error in ProtectedCategoryController.create");
        }
    }
}
