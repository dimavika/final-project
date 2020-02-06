package com.epam.service;

import com.epam.dao.DaoException;
import com.epam.dao.DaoHelper;
import com.epam.dao.DaoHelperFactory;
import com.epam.dao.review.ReviewDao;
import com.epam.dto.ReviewUserInfoDto;
import com.epam.entity.Review;

import java.util.List;

public class ReviewService {

    private DaoHelperFactory daoHelperFactory;

    public ReviewService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void addReview(String text, Long userId, Long audioId, String dateTime) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            ReviewDao dao = daoHelper.createReviewDao();
            Review review = new Review(0L, userId, text, audioId, dateTime);
            dao.save(review);
        } catch (DaoException | InterruptedException e) {
            throw new ServiceException(e);
        }
    }

    public List<ReviewUserInfoDto> getAllJoinUserByAudioId(Long audioId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            ReviewDao dao = daoHelper.createReviewDao();
            return dao.findAllJoinUserByAudioId(audioId);
        } catch (DaoException | InterruptedException e) {
            throw new ServiceException(e);
        }
    }
}
