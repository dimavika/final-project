package com.epam.spotify.command.audio;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class SendAudioIdAndPriceForOrderAdding implements Command {

    private static final String ADD_ORDER_PAGE = "controller?command=showAddOrder";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long audioId = Long.valueOf(request.getParameter("audioId"));
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter("price")));
        request.setAttribute("audioId", audioId);
        request.setAttribute("price", price);
        return CommandResult.forward(ADD_ORDER_PAGE);
    }
}
