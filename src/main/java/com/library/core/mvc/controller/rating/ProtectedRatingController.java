package com.library.core.mvc.controller.rating;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.rating.RatingDTO;
import com.library.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/protected/rating")
public class ProtectedRatingController extends RatingController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProtectedRatingController.class);

    @RequestMapping(value = "/my/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getMyRate(@PathVariable Long id) {
        Integer rate;
        try {
            rate = service.findMyRateByInstruction(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(new Integer(0), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingDTO> create(@RequestBody RatingDTO dto) {
        RatingDTO result = null;
        try {
            result = service.insert(dto);
        } catch (ServiceException e) {
            LOGGER.error("error in ProtectedInstructionController.create");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingDTO> update(@RequestBody RatingDTO dto) {
        RatingDTO instruction = null;
        try {
            instruction = service.update(dto);
        } catch (ManagerException e) {
            LOGGER.error("error in InstructionService.update()");
        }
        return new ResponseEntity<>(instruction, HttpStatus.OK);
    }
}
