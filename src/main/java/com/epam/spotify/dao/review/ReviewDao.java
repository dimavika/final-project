package com.epam.spotify.dao.review;

import com.epam.spotify.dao.Dao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dto.ReviewUserInfoDto;
import com.epam.spotify.entity.Review;

import java.util.List;

public interface ReviewDao extends Dao<Review> {

    List<ReviewUserInfoDto> findAllJoinUserByAudioId(Long audioId) throws DaoException;
}
