package com.epam.dao.order;

import com.epam.dao.AbstractDao;
import com.epam.dao.exception.DaoException;
import com.epam.dto.OrderAudioInfoDto;
import com.epam.entity.Order;
import com.epam.rowMapper.dto.OrderAudioInfoDtoRowMapper;

import java.sql.Connection;
import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    private static final String ADD_REVIEW = "INSERT Orders (audio_id, order_datetime, card_num, user_id, price) " +
            "VALUES(?,?,?,?,?)";
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
                item.getPrice());
    }

    @Override
    public List<OrderAudioInfoDto> findAllJoinAudioByUserId(Long userId) throws DaoException {
        return executeJoinQuery(FIND_BY_USER, new OrderAudioInfoDtoRowMapper(), userId);
    }
}
