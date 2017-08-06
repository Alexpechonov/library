package com.library.core.mvc.service.tag;

import com.library.core.mvc.service.core.GenericFacadeImpl;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.core.mvc.service.instruction.InstructionFacade;
import com.library.core.mvc.service.part.PartFacade;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.tag.Tag;
import com.library.dao.repository.tag.TagManager;
import com.library.dto.tag.TagDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
@Service
@Transactional
public class TagFacadeImpl extends GenericFacadeImpl<TagManager, TagDTO, Tag> implements TagFacade {
    private static final Logger logger = LoggerFactory.getLogger(TagFacadeImpl.class);

    @Autowired
    private TagManager manager;

    @Autowired
    private InstructionFacade instructionFacade;

    @Override
    protected TagManager getManager() {
        return manager;
    }


    @Override
    public Tag convertToModel(TagDTO dto) {
        Tag tag = new Tag();
        if (dto == null) {
            return tag;
        }
        tag.setId(dto.getId());
        tag.setName(dto.getName());
        return tag;
    }

    @Override
    public TagDTO convertToDTO(Tag tag) {
        TagDTO dto = new TagDTO();
        if (tag == null) {
            return dto;
        }
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        return dto;
    }

    @Override
    public void insertListIfNotExist(List<TagDTO> tags) throws ServiceException {
        for (TagDTO dto : tags) {
            if (manager.findByName(dto.getName()) == null) {
                insert(dto);
            }
        }
    }

    @Override
    public List<TagDTO> getUpdatedTags(List<TagDTO> tags) {
        for (TagDTO dto : tags) {
            dto.setId(manager.findByName(dto.getName()).getId());
        }
        return tags;
    }
}
