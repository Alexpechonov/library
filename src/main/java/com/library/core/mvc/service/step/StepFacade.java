package com.library.core.mvc.service.step;

import com.library.core.mvc.service.core.GenericFacade;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Step;
import com.library.dto.instruction.StepDTO;

import java.util.List;

/**
 * Created by user on 25.07.2017.
 */
public interface StepFacade extends GenericFacade<StepDTO, Step> {

    List<StepDTO> getUploadedImages(List<StepDTO> steps) throws ManagerException;
}
