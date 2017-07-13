package com.library.dao.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by user on 13.07.2017.
 */
public class ManagerException extends SQLException{

    private static final Logger logger = LoggerFactory.getLogger(ManagerException.class);

    public ManagerException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
        logger.error(reason);
    }

    public ManagerException(String reason, String SQLState) {
        super(reason, SQLState);
        logger.error(reason);
    }

    public ManagerException(String reason) {
        super(reason);
        logger.error(reason);
    }
}
