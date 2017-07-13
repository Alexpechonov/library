package com.library.dao.repository.user;

import com.library.dao.exceptions.ManagerException;
import com.library.dao.model.entities.user.User;

/**
 * Created by user on 13.07.2017.
 */
public interface UserManager {
    void insert(User user) throws ManagerException;

    User findById(Long id) throws ManagerException;

    User findByIdentity(String identity);
}
