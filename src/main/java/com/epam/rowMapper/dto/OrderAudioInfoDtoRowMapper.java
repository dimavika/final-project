package com.epam.rowMapper.dto;

import com.epam.dto.OrderAudioInfoDto;
import com.epam.rowMapper.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderAudioInfoDtoRowMapper implements RowMapper<OrderAudioInfoDto> {

    @Override
    public OrderAudioInfoDto map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(OrderAudioInfoDto.ID);
        Long audioId = resultSet.getLong(OrderAudioInfoDto.AUDIO_ID);
        String audioTitle = resultSet.getString(OrderAudioInfoDto.AUDIO_TITLE);
        String dateTime = resultSet.getString(OrderAudioInfoDto.DATE_TIME);
        BigDecimal price = resultSet.getBigDecimal(OrderAudioInfoDto.PRICE);
        return new OrderAudioInfoDto(id, audioId, audioTitle, dateTime, price);
    }
}
