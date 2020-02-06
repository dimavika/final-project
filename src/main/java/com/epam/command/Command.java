package com.epam.command;

import com.epam.dao.DaoException;
import com.epam.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Command {
     CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
