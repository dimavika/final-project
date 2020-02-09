package com.epam.rowMapper;

import com.epam.entity.Order;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Order.ID);
        Long audioId = resultSet.getLong(Order.AUDIO_ID);
        String dateTime = resultSet.getString(Order.DATE_TIME);
        String cardNumber = resultSet.getString(Order.CARD_NUMBER);
        Long userId = resultSet.getLong(Order.USER_ID);
        BigDecimal price = resultSet.getBigDecimal(Order.PRICE);
        return new Order(id, audioId, dateTime, cardNumber, userId, price);
    }
}
