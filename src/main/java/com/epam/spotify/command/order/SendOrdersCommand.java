package com.epam.spotify.command.order;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.dto.OrderAudioInfoDto;
import com.epam.spotify.entity.User;
import com.epam.spotify.service.OrderService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SendOrdersCommand implements Command {

    private OrderService service;

    private final static String ORDERS_PAGE = "controller?command=showOrders";

    public SendOrdersCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        List<OrderAudioInfoDto> orders = service.getAllJoinAudioByUserId(userId);
        request.setAttribute("orders", orders);
        return CommandResult.forward(ORDERS_PAGE);
    }
}
