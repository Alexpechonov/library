package com.library.core.mvc.service.medal;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.core.mvc.service.user.UserFacade;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.medal.Medal;
import com.library.dao.model.entities.user.User;
import com.library.dao.repository.comment.CommentManager;
import com.library.dao.repository.instruction.InstructionManager;
import com.library.dao.repository.medal.MedalManager;
import com.library.dto.medal.MedalDTO;
import com.library.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01.08.2017.
 */
@Service
@Transactional
public class MedalFacadeImpl extends GenericFacadeImpl<MedalManager, MedalDTO, Medal> implements MedalFacade {

    @Autowired
    private MedalManager manager;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CommentManager commentManager;

    @Autowired
    private InstructionManager instructionManager;

    @Override
    protected MedalManager getManager() {
        return manager;
    }

    @Override
    public Medal convertToModel(MedalDTO dto) {
        Medal medal = new Medal();
        medal.setId(dto.getId());
        medal.setName(dto.getName());
        medal.setImage(dto.getImage());
        return medal;
    }

    @Override
    public MedalDTO convertToDTO(Medal medal) {
        MedalDTO dto = new MedalDTO();
        dto.setId(medal.getId());
        dto.setName(medal.getName());
        dto.setImage(medal.getImage());
        return dto;
    }

    @Override
    public MedalDTO insert(MedalDTO dto) throws ServiceException {
        if (manager.findByName(dto.getName()) != null) {
            return null;
        }
        return super.insert(dto);
    }


    @Override
    public void checkInstructions() throws ServiceException {
        Long userId = userFacade.getMe().getId();
        Integer count = instructionManager.findAllByUser(userId).size();
        switch (count) {
            case 1:
                addMedal("Novice writer", userId);
                break;
            case 5:
                addMedal("Writer", userId);
                break;
            case 10:
                addMedal("Expert", userId);
                break;
        }
    }

    @Override
    public void checkComments() throws ServiceException {
        Long userId = userFacade.getMe().getId();
        Integer count = commentManager.getAllByUser(userId).size();
        switch (count) {
            case 1:
                addMedal("Novice commentator", userId);
                break;
            case 5:
                addMedal("Commentator", userId);
                break;
        }
    }

    @Override
    public void addMedal(String name, Long userId) throws ServiceException {
        try {
            UserDTO user = userFacade.findById(userId);
            if (checkIfExist(name, user)) return;
            user.getMedals().add(convertToDTO(manager.findByName(name)));
            userFacade.update(user);
        } catch (ManagerException e) {
            throw new ServiceException("Error in MedalFacade.addMedal");
        }
    }

    private boolean checkIfExist(String name, UserDTO user) {
        for (MedalDTO dto : user.getMedals()) {
            if (dto.getName().equals(name)) return true;
        }
        return false;
    }

    @Override
    public void deleteMedal(String name, Long userId) throws ServiceException {
        try {
            UserDTO user = removeMedal(name, userFacade.findById(userId));
            if (user == null) return;
            userFacade.update(user);
        } catch (ManagerException e) {
            throw new ServiceException("Error in MedalFacade.addMedal");
        }
    }

    private UserDTO removeMedal(String name, UserDTO user) {
        for (MedalDTO dto : user.getMedals()) {
            if (dto.getName().equals(name)) {
                user.getMedals().remove(dto);
                return user;
            }
        }
        return null;
    }

    @Override
    public List<Medal> convertToModelList(List<MedalDTO> dtos) {
        if (dtos == null) return null;
        List<Medal> medals = new ArrayList<>();
        for (MedalDTO dto : dtos) {
            medals.add(convertToModel(dto));
        }
        return medals;
    }
}
