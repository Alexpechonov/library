package com.library.core.mvc.controller.part;

import com.library.dao.exceptions.ManagerException;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.instruction.PartDTO;
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
 * Created by user on 25.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/protected/part")
public class ProtectedPartController extends PartController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProtectedPartController.class);

    @RequestMapping(value = "/{instructionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PartDTO> updatePart(@PathVariable Long instructionId, @RequestBody PartDTO dto) {
        PartDTO part = null;
        try {
            part = service.update(dto, instructionId);
        } catch (ManagerException e) {
            LOGGER.error("error in PartService.update()");
        }
        return new ResponseEntity<PartDTO>(part, HttpStatus.OK);
    }
}
