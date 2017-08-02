package com.library.core.mvc.service.rating;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.rating.Rating;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.rating.RatingManager;
import com.library.dto.instruction.InstructionDTO;
import com.library.dto.rating.RatingDTO;
import com.library.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user on 01.08.2017.
 */
@Service
@Transactional
public class RatingFacadeImpl extends GenericFacadeImpl<RatingManager, RatingDTO, Rating> implements RatingFacade {

    @Autowired
    private RatingManager manager;

    @Override
    protected RatingManager getManager() {
        return manager;
    }

    @Override
    public Rating convertToModel(RatingDTO dto) {
        Rating rating = new Rating();
        if (dto == null) return rating;
        rating.setId(dto.getId());
        rating.setRate(dto.getRate());
        User user = new User();
        user.setId(dto.getUser().getId());
        Instruction instruction = new Instruction();
        instruction.setId(dto.getInstruction().getId());
        rating.setUser(user);
        rating.setInstruction(instruction);
        return rating;
    }

    @Override
    public RatingDTO convertToDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        if (rating == null) return dto;
        dto.setId(rating.getId());
        dto.setRate(rating.getRate());
        UserDTO user = new UserDTO();
        user.setId(rating.getUser().getId());
        InstructionDTO instruction = new InstructionDTO();
        instruction.setId(rating.getInstruction().getId());
        dto.setUser(user);
        dto.setInstruction(instruction);
        return dto;
    }

    @Override
    public RatingDTO findByUserAndInstruction(Long userId, Long instructionId) throws ServiceException{
        try {
            return convertToDTO(manager.getByUserAndInstruction(userId, instructionId));
        } catch (ManagerException e) {
            throw new ServiceException("Error in RatingFacade.findByUserAndInstruction");
        }
    }

    @Override
    public RatingDTO update(RatingDTO dto) throws ManagerException {
        Rating rating = manager.getByUserAndInstruction(dto.getUser().getId(), dto.getInstruction().getId());
        if (rating != null) {
            dto.setId(rating.getId());
        }
        return super.update(dto);
    }

    @Override
    public List<RatingDTO> findAllByInstruction(Long instructionId) throws ServiceException {
        try {
            return convertToDTOList(manager.findAllByInstruction(instructionId));
        } catch (ManagerException e) {
            throw new ServiceException("Error in RatingFacade.findAllByInstruction");
        }
    }
}
