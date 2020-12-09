package com.epam.spotify.service;

import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dao.user.UserDao;
import com.epam.spotify.entity.User;
import com.epam.spotify.service.exception.ServiceException;
import com.epam.spotify.dao.DaoHelper;
import com.epam.spotify.dao.DaoHelperFactory;

import java.util.List;
import java.util.Optional;

public class UserService {

    private DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            return dao.findAll();
        }catch (DaoException  e){
            throw new ServiceException(e);
        }
    }

    public void blockUser(Long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            dao.updateIsBlockedByTrue(id);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public void unblockUser(Long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            dao.updateIsBlockedByFalse(id);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

//    public void register(String showAllAudios, String password, String name, int id) throws ServiceException {
//        try (DaoHelper daoHelper = daoHelperFactory.create()) {
//            User user = new User(name, id, showAllAudios, password);
//            UserDao dao = daoHelper.createUserDao();
//            dao.save(user);
//        } catch (DaoException e) {
//            throw new ServiceException(e);
//        }
//    }
}
