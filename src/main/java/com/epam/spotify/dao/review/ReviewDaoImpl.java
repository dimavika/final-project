package com.epam.spotify.dao.review;

import com.epam.spotify.dao.AbstractDao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dto.ReviewUserInfoDto;
import com.epam.spotify.entity.Review;
import com.epam.spotify.rowMapper.dto.ReviewUserInfoDtoRowMapper;

import java.sql.Connection;
import java.util.List;

public class ReviewDaoImpl extends AbstractDao<Review> implements ReviewDao {

    private static final String ADD_REVIEW = "INSERT Reviews (user_id, text, audio_id, review_datetime) VALUES(?,?,?,?)";
    private static final String FIND_ALL_JOIN_USER_BY_AUDIO_ID = "SELECT * FROM Reviews " +
            "inner join Users on Reviews.user_id = Users.id " +
            "WHERE audio_id = ?";

    public ReviewDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return Review.TABLE;
    }

    @Override
    public void save(Review item) throws DaoException {
        executeUpdate(ADD_REVIEW, item.getUserId(), item.getText(), item.getAudioId(), item.getDateTime());
    }

    @Override
    public List<ReviewUserInfoDto> findAllJoinUserByAudioId(Long audioId) throws DaoException {
        return executeJoinQuery(FIND_ALL_JOIN_USER_BY_AUDIO_ID, new ReviewUserInfoDtoRowMapper(), audioId);
    }
}
