package com.library.core.mvc.service.tag;

import com.library.core.mvc.service.exception.ServiceException;
import com.library.dto.tag.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagFacade facade;

    @Override
    public void insert(TagDTO dto) throws ServiceException {
        facade.insert(dto);
    }

    @Override
    public List<TagDTO> getAll() {
        return facade.getAll();
    }
}
