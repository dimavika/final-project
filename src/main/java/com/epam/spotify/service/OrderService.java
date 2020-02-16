package com.epam.spotify.service;

import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dao.DaoHelper;
import com.epam.spotify.dao.DaoHelperFactory;
import com.epam.spotify.dao.order.OrderDao;
import com.epam.spotify.dto.OrderAudioInfoDto;
import com.epam.spotify.entity.Order;
import com.epam.spotify.service.exception.ServiceException;
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

    public void addOrder(Long audioId, String dateTime, String cardNumber, Long userId, BigDecimal price,
                         String year, String thirdNum) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            OrderDao orderDao = daoHelper.createOrderDao();
            Order order = new Order(0L, audioId, dateTime, cardNumber, userId, price, year, thirdNum);
            orderDao.save(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
