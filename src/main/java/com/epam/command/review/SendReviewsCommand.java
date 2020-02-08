package com.epam.command.review;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.dto.ReviewUserInfoDto;
import com.epam.service.ReviewService;
import com.epam.service.exception.ServiceException;

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
