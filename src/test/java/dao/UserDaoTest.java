package dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import enums.UserStatus;
import model.User;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {

  private UserDao userDao;

  @Before
  public void initialize() {
    userDao = new UserDao();
  }

  @Test
  public void createUserTest() {
    User user = new User();
    user.setEmail("abc@test.com");
    user.setFirstName("Test");
    user.setLastName("Test");
    user.setMobile("981998898");
    user.setStatus(UserStatus.ACTIVE);
    Integer id = userDao.createUser(user);
    assertNotNull(id);
  }

  @Test
  public void fetchUserTest() {
    User user = new User();
    user.setEmail("abc@example.com");
    user.setFirstName("Test");
    user.setLastName("Test");
    user.setMobile("981998898");
    user.setStatus(UserStatus.ACTIVE);
    Integer id = userDao.createUser(user);

    User u = userDao.getUser(id);
    assertNotNull(u);
    assertEquals(u.getEmail(), user.getEmail());
    assertEquals(u.getFirstName(), user.getFirstName());
    assertEquals(u.getId(), id);
    assertEquals(u.getLastName(), user.getLastName());
    assertEquals(u.getMobile(), user.getMobile());
  }
}
