package com.epam.command.order;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.entity.User;
import com.epam.service.OrderService;
import com.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddOrderCommand implements Command {

    private OrderService service;

    private final static String AUDIOS_PAGE = "controller?command=audios";

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter("price")));
        service.addOrder(audioId, dateTime, cardNumber, userId, price);
        return CommandResult.redirect(AUDIOS_PAGE);
    }
}
