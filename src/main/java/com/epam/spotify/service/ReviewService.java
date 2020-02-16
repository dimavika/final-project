package com.epam.spotify.service;

import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dao.DaoHelper;
import com.epam.spotify.dao.DaoHelperFactory;
import com.epam.spotify.dao.review.ReviewDao;
import com.epam.spotify.dto.ReviewUserInfoDto;
import com.epam.spotify.entity.Review;
import com.epam.spotify.service.exception.ServiceException;

import java.util.List;

public class ReviewService {

    private DaoHelperFactory daoHelperFactory;

    public ReviewService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void addReview(String text, Long userId, Long audioId, String dateTime) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ReviewDao dao = daoHelper.createReviewDao();
            Review review = new Review(0L, userId, text, audioId, dateTime);
            dao.save(review);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<ReviewUserInfoDto> getAllJoinUserByAudioId(Long audioId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ReviewDao dao = daoHelper.createReviewDao();
            return dao.findAllJoinUserByAudioId(audioId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
