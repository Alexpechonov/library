package com.library.core.mvc.service.tag;

import com.library.core.mvc.service.core.GenericServiceImpl;
import com.library.dto.tag.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 21.07.2017.
 */
@Service
public class TagServiceImpl extends GenericServiceImpl<TagFacade, TagDTO>  implements TagService {
    @Autowired
    TagFacade facade;

    @Override
    protected TagFacade getFacade() { return facade; }
}
