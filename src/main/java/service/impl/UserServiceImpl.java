package service.impl;

import dao.BaseDaoRegistry;
import dao.UserDao;
import enums.UserStatus;
import exception.UserException;
import model.User;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import pojo.UserPojo;
import service.UserService;
import utils.NullOrEmptyCheckerUtil;


public class UserServiceImpl implements UserService {

  BaseDaoRegistry baseDaoRegistry = BaseDaoRegistry.getBaseDaoRegistry();

  UserDao userDao = baseDaoRegistry.getUserDaoInstance();

  @Override
  public Integer addUser(UserPojo userPojo) throws UserException {
    try {
      User user = new User();
      validateAddUser(userPojo);
      user.setMobile(userPojo.getMobile());
      user.setEmail(userPojo.getEmail());
      user.setFirstName(userPojo.getFirstName());
      user.setLastName(userPojo.getLastName());
      user.setStatus(UserStatus.ACTIVE);
      Integer id = userDao.createUser(user);
      return id;
    } catch (UserException usex) {
      throw usex;
    } catch (ConstraintViolationException e) {
      throw new UserException(400, "User email already exist");
    } catch (Exception exc) {
      throw new UserException(500, ExceptionUtils.getMessage(exc));
    }
  }

  private void validateAddUser(UserPojo userPojo) throws UserException {
    if (NullOrEmptyCheckerUtil.isNullOrEmpty(userPojo.getFirstName()) || NullOrEmptyCheckerUtil
        .isNullOrEmpty(userPojo.getMobile()) || NullOrEmptyCheckerUtil
        .isNullOrEmpty(userPojo.getEmail())) {
      throw new UserException(400, "Missing madnatory params");
    }
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
