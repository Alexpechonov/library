package com.library.dao.repository.tag;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.tag.Tag;

import java.util.List;

/**
 * Created by user on 21.07.2017.
 */
public interface TagManager {
    void insert(Tag tag) throws ManagerException;

    List<Tag> getAll();

    Tag findByName(String name);
}
