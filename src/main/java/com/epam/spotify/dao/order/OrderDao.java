package com.epam.spotify.dao.order;

import com.epam.spotify.dao.Dao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dto.OrderAudioInfoDto;
import com.epam.spotify.entity.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {

    List<OrderAudioInfoDto> findAllJoinAudioByUserId(Long userId) throws DaoException;
}
