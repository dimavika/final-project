package com.epam.spotify.command.order;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.entity.User;
import com.epam.spotify.service.OrderService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddOrderCommand implements Command {

    private OrderService service;

    private final static String ORDERS_PAGE = "controller?command=sendOrders";

    public AddOrderCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long audioId = Long.valueOf(request.getParameter("audioId"));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = format.format(date);
        String cardNumber = request.getParameter("cardNum");
        String year = request.getParameter("year");
        String thirdNum = request.getParameter("thirdNum");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter("price")));
        service.addOrder(audioId, dateTime, cardNumber, userId, price, year, thirdNum);
        return CommandResult.redirect(ORDERS_PAGE);
    }
}
