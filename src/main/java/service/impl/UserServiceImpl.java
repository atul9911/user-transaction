package service.impl;

import dao.BaseDaoRegistry;
import dao.UserDao;
import exception.UserException;
import model.User;
import pojo.UserPojo;
import service.UserService;


public class UserServiceImpl implements UserService {
  BaseDaoRegistry baseDaoRegistry = BaseDaoRegistry.getBaseDaoRegistry();

  UserDao userDao = baseDaoRegistry.getUserDaoInstance();

  @Override
  public Integer addUser(UserPojo userPojo) throws UserException {
    User user = new User();
    user.setMobile(userPojo.getMobile());
    user.setEmail(userPojo.getEmail());
    user.setFirstName(userPojo.getFirstName());
    user.setLastName(userPojo.getFirstName());
    Integer id = userDao.createUser(user);
    return id;
  }

  @Override
  public UserPojo validateUser(Integer id) throws UserException {
    User user = userDao.getUser(id);
    UserPojo userPojo = new UserPojo();
    userPojo.setEmail(user.getEmail());
    userPojo.setFirstName(user.getFirstName());
    userPojo.setLastName(user.getLastName());
    userPojo.setMobile(user.getMobile());
    return userPojo;
  }

}
