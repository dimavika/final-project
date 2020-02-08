package com.epam.command;

import com.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
     CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
