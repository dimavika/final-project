package com.epam.dao.review;

import com.epam.dao.Dao;
import com.epam.dao.DaoException;
import com.epam.dto.ReviewUserInfoDto;
import com.epam.entity.Review;

import java.util.List;

public interface ReviewDao extends Dao<Review> {

    List<ReviewUserInfoDto> findAllJoinUserByAudioId(Long audioId) throws DaoException;
}
