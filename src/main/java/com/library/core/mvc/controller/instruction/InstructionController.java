package com.library.core.mvc.controller.instruction;

import com.library.core.mvc.service.instruction.InstructionService;
import com.library.dao.exceptions.ManagerException;
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
 * Created by user on 21.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/open/instruction")
public class InstructionController {
    private final static Logger LOGGER = LoggerFactory.getLogger(InstructionController.class);

    @Autowired
    protected InstructionService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InstructionDTO> getById(@PathVariable Long id) {
        InstructionDTO dto;
        try {
            dto = service.findById(id);
        } catch (ManagerException e) {
            return new ResponseEntity<InstructionDTO>(new InstructionDTO(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<InstructionDTO>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InstructionDTO>> getAll() {
        return new ResponseEntity<List<InstructionDTO>>(service.findAll(), HttpStatus.OK);
    }
}
