package com.library.core.mvc.service.rating;

import com.library.core.mvc.service.core.GenericServiceImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.core.mvc.service.user.UserFacade;
import com.library.dao.exceptions.ManagerException;
import com.library.dto.rating.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 01.08.2017.
 */
@Service
public class RatingServiceImpl extends GenericServiceImpl<RatingFacade, RatingDTO> implements RatingService {

    @Autowired
    private RatingFacade facade;

    @Autowired
    private UserFacade userFacade;

    @Override
    protected RatingFacade getFacade() {
        return facade;
    }

    @Override
    public Integer findMyRateByInstruction(Long instructionId) throws ServiceException {
        RatingDTO dto = facade.findByUserAndInstruction(userFacade.getMe().getId(), instructionId);
        if (dto == null) {
            throw new ServiceException("Not found");
        }
        return dto.getRate();
    }

    @Override
    public Float getRatingOfInstruction(Long instructionId) throws ServiceException {
        List<RatingDTO> dtoList = facade.findAllByInstruction(instructionId);
        Float sum = new Float(0);
        for (RatingDTO dto: dtoList) {
            sum += (float)dto.getRate();
        }
        return sum/dtoList.size();
    }

    @Override
    public RatingDTO insert(RatingDTO dto) throws ServiceException {
        dto.setUser(userFacade.getMe());
        return super.insert(dto);
    }

    @Override
    public RatingDTO update(RatingDTO dto) throws ManagerException {
        dto.setUser(userFacade.getMe());
        return super.update(dto);
    }
}
