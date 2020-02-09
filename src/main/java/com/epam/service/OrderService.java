package com.epam.service;

import com.epam.dao.exception.DaoException;
import com.epam.dao.DaoHelper;
import com.epam.dao.DaoHelperFactory;
import com.epam.dao.order.OrderDao;
import com.epam.dto.OrderAudioInfoDto;
import com.epam.entity.Order;
import com.epam.service.exception.ServiceException;
import java.math.BigDecimal;
import java.util.List;

public class OrderService {

    private DaoHelperFactory daoHelperFactory;

    public OrderService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<OrderAudioInfoDto> getAllJoinAudioByUserId(Long userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            OrderDao orderDao = daoHelper.createOrderDao();
            return orderDao.findAllJoinAudioByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void addOrder(Long audioId, String dateTime, String cardNumber, Long userId, BigDecimal price) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            OrderDao orderDao = daoHelper.createOrderDao();
            Order order = new Order(0L, audioId, dateTime, cardNumber, userId, price);
            orderDao.save(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
