package com.library.core.mvc.controller.instruction;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.core.mvc.service.instruction.InstructionService;
import com.library.dto.instruction.InstructionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 21.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/instruction")
public class InstructionController {
    private final static Logger LOGGER = LoggerFactory.getLogger(InstructionController.class);

    @Autowired
    private InstructionService service;


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InstructionDTO> create(@RequestBody InstructionDTO dto) {
        InstructionDTO result = null;
        try {
            result = service.insert(dto);
        } catch (ServiceException e) {
            LOGGER.error("error in InstructionController.create");
        }
        return new ResponseEntity<InstructionDTO>(result, HttpStatus.OK);
    }




}
