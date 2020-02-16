package com.epam.spotify.command;

import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
     CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
