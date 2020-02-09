package com.epam.command.audio;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.service.exception.ServiceException;

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
