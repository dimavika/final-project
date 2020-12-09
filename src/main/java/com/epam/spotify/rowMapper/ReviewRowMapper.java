package com.epam.spotify.rowMapper;

import com.epam.spotify.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {

    @Override
    public Review map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Review.ID);
        Long userId = resultSet.getLong(Review.USER_ID);
        String text = resultSet.getString(Review.TEXT);
        Long audioId = resultSet.getLong(Review.AUDIO_ID);
        String dateTime = resultSet.getString(Review.DATE_TIME);
        return new Review(id, userId, text, audioId, dateTime);
    }
}
