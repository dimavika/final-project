package com.epam.dao.order;

import com.epam.dao.Dao;
import com.epam.dao.exception.DaoException;
import com.epam.dto.OrderAudioInfoDto;
import com.epam.entity.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {

    List<OrderAudioInfoDto> findAllJoinAudioByUserId(Long userId) throws DaoException;
}
