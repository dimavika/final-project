package com.epam.spotify.dao.order;

import com.epam.spotify.dao.AbstractDao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dto.OrderAudioInfoDto;
import com.epam.spotify.entity.Order;
import com.epam.spotify.rowMapper.dto.OrderAudioInfoDtoRowMapper;

import java.sql.Connection;
import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    private static final String ADD_REVIEW = "INSERT Orders (audio_id, order_datetime, card_num, user_id, " +
            "price, year, third_num) VALUES(?,?,?,?,?,?,?)";
    private static final String FIND_BY_USER = "SELECT * FROM orders " +
            "inner join Audios on Orders.audio_id = Audios.id WHERE user_id = ?";

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return Order.TABLE;
    }

    @Override
    public void save(Order item) throws DaoException {
        executeUpdate(ADD_REVIEW, item.getAudioId(), item.getDateTime(), item.getCardNumber(), item.getUserId(),
                item.getPrice(), item.getYear(), item.getThirdNum());
    }

    @Override
    public List<OrderAudioInfoDto> findAllJoinAudioByUserId(Long userId) throws DaoException {
        return executeJoinQuery(FIND_BY_USER, new OrderAudioInfoDtoRowMapper(), userId);
    }
}
