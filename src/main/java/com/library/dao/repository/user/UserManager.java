package com.library.dao.repository.user;

import com.library.dao.model.entities.user.User;
import com.library.dao.repository.core.GenericManager;

/**
 * Created by user on 13.07.2017.
 */
public interface UserManager extends GenericManager<User> {

    User findByUserName(String userName);

    User findByIdentity(String identity);
}
