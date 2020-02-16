package com.epam.spotify.command.audio;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendAudioIdForReviewAdding implements Command {

    private static final String ADD_REVIEW_PAGE = "controller?command=showAddReview";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long audioId = Long.valueOf(request.getParameter("audioId"));
        request.setAttribute("audioId", audioId);
        return CommandResult.forward(ADD_REVIEW_PAGE);
    }
}
