package com.epam.service;

import com.epam.dao.*;
import com.epam.dao.user.UserDao;
import com.epam.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            UserDao dao = daoHelper.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException | InterruptedException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            UserDao dao = daoHelper.createUserDao();
            return dao.findAll();
        }catch (DaoException | InterruptedException e){
            throw new ServiceException(e);
        }
    }

    public void blockUser(Long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            UserDao dao = daoHelper.createUserDao();
            dao.updateIsBlockedByTrue(id);
        }catch (DaoException | InterruptedException e){
            throw new ServiceException(e);
        }
    }

    public void unblockUser(Long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            UserDao dao = daoHelper.createUserDao();
            dao.updateIsBlockedByFalse(id);
        }catch (DaoException | InterruptedException e){
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
