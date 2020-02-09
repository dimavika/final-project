package com.epam.command.order;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.dto.OrderAudioInfoDto;
import com.epam.entity.User;
import com.epam.service.OrderService;
import com.epam.service.exception.ServiceException;

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
