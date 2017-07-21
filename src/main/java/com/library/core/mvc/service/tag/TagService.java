package com.library.core.mvc.service.tag;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dto.tag.TagDTO;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public interface TagService {
    void insert(TagDTO dto) throws ServiceException;

    List<TagDTO> getAll();
}
