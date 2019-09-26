package dao;

import model.User;
import org.hibernate.Session;
import utils.HibernateUtil;

public class UserDao implements BaseDao {

  public Integer createUser(User user) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Integer id = (Integer) session.save(user);
    session.getTransaction().commit();
    return id;
  }

  public User getUser(Integer id) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    User user = (User) session.get(User.class, id);
    session.getTransaction().commit();
    return user;
  }

  public UserDao getDaoObject() {
    return new UserDao();
  }
}
