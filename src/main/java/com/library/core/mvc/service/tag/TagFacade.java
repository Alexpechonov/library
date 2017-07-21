package com.library.core.mvc.service.tag;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.model.entities.tag.Tag;
import com.library.dto.tag.TagDTO;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public interface TagFacade {
    List<TagDTO> getAll();

    void insert(TagDTO dto) throws ServiceException;

    Tag convertToModel(TagDTO dto);

    TagDTO convertToDTO(Tag tag);
}
