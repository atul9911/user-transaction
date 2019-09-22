package dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class BaseDaoRegistryTest {
  @Test
  public void testGetUserDaoInstance(){
    UserDao userDao = BaseDaoRegistry.getBaseDaoRegistry().getUserDaoInstance();
    assertNotNull(userDao);
  }
}
