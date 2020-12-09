package com.epam.spotify.rowMapper.dto;

import com.epam.spotify.dto.ReviewUserInfoDto;
import com.epam.spotify.entity.Review;
import com.epam.spotify.rowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewUserInfoDtoRowMapper implements RowMapper<ReviewUserInfoDto> {
    @Override
    public ReviewUserInfoDto map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Review.ID);
        String user = resultSet.getString(ReviewUserInfoDto.USER);
        String text = resultSet.getString(Review.TEXT);
        String dateTime = resultSet.getString(Review.DATE_TIME);
        return new ReviewUserInfoDto(id, user, text, dateTime);
    }
}