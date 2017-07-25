package com.library.dao.repository.tag;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.instruction.Instruction;
import com.library.dao.model.entities.tag.Tag;
import com.library.dao.repository.core.GenericManager;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public interface TagManager extends GenericManager<Tag> {

    Tag findByName(String name);

}
