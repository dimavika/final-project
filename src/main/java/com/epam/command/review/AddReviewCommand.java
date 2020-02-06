package com.epam.command.review;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.service.ReviewService;
import com.epam.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReviewCommand implements Command {

    private ReviewService service;

    public AddReviewCommand(ReviewService service) {
        this.service = service;
    }

    private static final String AUDIOS_PAGE = "controller?command=audios";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        String text = request.getParameter("text");
        Long audioId = Long.valueOf(request.getParameter("audioId"));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = format.format(date);
        service.addReview(text, userId, audioId, dateTime);
        return CommandResult.redirect(AUDIOS_PAGE);
    }
}
