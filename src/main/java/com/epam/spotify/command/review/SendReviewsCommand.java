package com.epam.spotify.command.review;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.dto.ReviewUserInfoDto;
import com.epam.spotify.service.ReviewService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendReviewsCommand implements Command {

    private static final String REVIEWS_PAGE = "controller?command=showReviews";

    private ReviewService service;

    public SendReviewsCommand(ReviewService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long audioId = Long.valueOf(request.getParameter("audioId"));
        List<ReviewUserInfoDto> reviews = service.getAllJoinUserByAudioId(audioId);
        request.setAttribute("reviews", reviews);
        return CommandResult.forward(REVIEWS_PAGE);
    }
}
