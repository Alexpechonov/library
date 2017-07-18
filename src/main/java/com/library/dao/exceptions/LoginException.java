package com.library.dao.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by user on 18.07.2017.
 */
public class LoginException extends Exception{

    private static final Logger logger = LoggerFactory.getLogger(ManagerException.class);

    public LoginException(String reason) {
        logger.error(reason);
    }
}