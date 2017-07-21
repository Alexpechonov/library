package com.library.core.mvc.service.tag;

import com.library.core.mvc.service.exception.ServiceException;
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
public class TagFacadeImpl implements TagFacade {
    private static final Logger logger = LoggerFactory.getLogger(TagFacadeImpl.class);

    @Autowired
    private TagManager manager;

    @Override
    public List<TagDTO> getAll() {
        return convertToDtoList(manager.getAll());
    }

    @Override
    public void insert(TagDTO dto) throws ServiceException {
        Tag tag = convertToModel(dto);
        Tag tagByName = manager.findByName(tag.getName());
        if (tagByName != null) {
            throw new ServiceException("tag with name '" + tag.getName() + "' already exist");
        }
        try {
            manager.insert(tag);
        } catch (ManagerException e) {
            logger.error("error in UserManager.insert", e);
        }
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

    public List<TagDTO> convertToDtoList(List<Tag> tags) {
        List<TagDTO> result = new ArrayList<TagDTO>();
        for (Tag tag : tags) {
            result.add(convertToDTO(tag));
        }
        return result;
    }
}
