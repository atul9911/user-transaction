package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import exception.UserException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import pojo.UserPojo;

public class UserServiceTest {

  private UserService userService;

  @Before
  public void intitalize() {
    userService = (UserService) BaseServiceRegistry.getService("user");
  }

  private static String randomestring() {
    String generatedstring = RandomStringUtils.randomAlphabetic(8);
    return (generatedstring) + "@gmail.com";
  }

  @Test
  public void createUserTest() {
    UserPojo userPojo = new UserPojo();
    userPojo.setMobile("9871234");
    userPojo.setFirstName("abc");
    userPojo.setLastName("xyz");
    userPojo.setEmail(randomestring());
    Integer id = userService.addUser(userPojo);
    assertNotNull(id);
  }

  @Test
  public void validateUserTest() {
    UserPojo userPojo = new UserPojo();
    userPojo.setMobile("9871234");
    userPojo.setFirstName("abc");
    userPojo.setLastName("xyz");
    userPojo.setEmail(randomestring());
    Integer id = userService.addUser(userPojo);
    UserPojo user = userService.validateUser(id);
    assertEquals(user.getFirstName(), userPojo.getFirstName());
    assertEquals(user.getLastName(), userPojo.getLastName());
    assertEquals(user.getEmail(), userPojo.getEmail());
    assertEquals(user.getMobile(), userPojo.getMobile());
    assertNotNull(id);
  }

  @Test
  public void testDuplicateEmail() {
    try {
      UserPojo userPojo = new UserPojo();
      userPojo.setMobile("9871234");
      userPojo.setFirstName("abc");
      userPojo.setLastName("xyz");
      userPojo.setEmail(randomestring());
      Integer id = userService.addUser(userPojo);
      assertNotNull(id);
    } catch (UserException exc) {
      assertEquals(exc.getStatusCode(),400);
      assertEquals(exc.getMessage(),"User email already exist");
    }
  }

  @Test
  public void testEmptyEmail() {
    try {
      UserPojo userPojo = new UserPojo();
      userPojo.setMobile("9871234");
      userPojo.setFirstName("abc");
      userPojo.setLastName("xyz");
      Integer id = userService.addUser(userPojo);
      assertNotNull(id);
    } catch (UserException exc) {
      assertEquals(exc.getStatusCode(),400);
      assertEquals(exc.getMessage(),"Missing madnatory params");
    }
  }

  @Test
  public void testEmptyMobile() {
    try {
      UserPojo userPojo = new UserPojo();
      userPojo.setFirstName("abc");
      userPojo.setLastName("xyz");
      userPojo.setEmail(randomestring());
      Integer id = userService.addUser(userPojo);
      assertNotNull(id);
    } catch (UserException exc) {
      assertEquals(exc.getStatusCode(),400);
      assertEquals(exc.getMessage(),"Missing madnatory params");
    }
  }

  @Test
  public void testEmptyFirstName() {
    try {
      UserPojo userPojo = new UserPojo();
      userPojo.setLastName("xyz");
      userPojo.setMobile("9871234");
      userPojo.setEmail(randomestring());
      Integer id = userService.addUser(userPojo);
      assertNotNull(id);
    } catch (UserException exc) {
      assertEquals(exc.getStatusCode(),400);
      assertEquals(exc.getMessage(),"Missing madnatory params");
    }
  }
}
