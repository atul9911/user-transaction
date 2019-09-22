package service;


import exception.UserException;
import pojo.UserPojo;

public interface UserService extends BaseService {
    Integer addUser(UserPojo userPojo) throws UserException;
    UserPojo validateUser(Integer id) throws  UserException;
}
