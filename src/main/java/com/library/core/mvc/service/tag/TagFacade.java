package com.library.core.mvc.service.tag;

import com.library.core.mvc.service.core.GenericFacade;
import com.library.core.mvc.service.exception.ServiceException;
import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.tag.Tag;
import com.library.dto.tag.TagDTO;

import java.util.List;


/**
 * Created by user on 21.07.2017.
 */
public interface TagFacade extends GenericFacade<TagDTO, Tag>{
    void insertListIfNotExist(List<TagDTO> tags) throws ServiceException;

    List<TagDTO> getUpdatedTags(List<TagDTO> tags);
}
