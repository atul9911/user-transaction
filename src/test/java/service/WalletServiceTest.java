package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import dao.UserDao;
import enums.UserStatus;
import exception.WalletException;
import model.User;
import model.Wallet;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WalletServiceTest {

  private static UserDao userDao;

  private WalletService walletService;

  private static User user;

  private static Integer createUser(User user) throws Exception {
    return userDao.createUser(user);
  }

  private static String randomestring() {
    String generatedstring = RandomStringUtils.randomAlphabetic(8);
    return (generatedstring) + "@gmail.com";
  }

  @BeforeClass
  public static void intializeUser() throws Exception {
    userDao = new UserDao();
    user = new User();
    user.setEmail(randomestring());
    user.setFirstName("Test");
    user.setLastName("Test");
    user.setMobile("981998898");
    user.setStatus(UserStatus.ACTIVE);
    Integer userId = createUser(user);
  }

  @Before
  public void initialize() {
    walletService = (WalletService) BaseServiceRegistry.getService("wallet");
  }

  @Test
  public void createWalletTest() throws Exception {
    user.setEmail(randomestring());
    Integer userId = createUser(user);
    User response = userDao.getUser(userId);
    Integer id = walletService.addWallet(response);
    assertNotNull(id);
  }

  @Test
  public void validateWalletTest() throws Exception {
    String email = randomestring();
    user.setEmail(email);
    Integer id = createUser(user);
    Integer walletId = walletService.addWallet(user);
    Wallet wallet = walletService.validateWallet(walletId);
    assertNotNull(walletId);
    assertEquals(wallet.getUserId(), id);
  }

  @Test
  public void addMoneyToWalletTest() throws Exception {
    user.setEmail(randomestring());
    Integer userId = createUser(user);
    User response = userDao.getUser(userId);
    Integer walletId = walletService.addWallet(response);
    walletService.addMoneyToWallet(500.00, walletId);
    Wallet wallet = walletService.validateWallet(walletId);
    assertEquals(wallet.getId(), walletId);
    assertEquals(wallet.getBalance(), 500.00D, 0.00);
  }

  @Test
  public void duplicateWalletTest() {
    try {
      user.setEmail(randomestring());
      Integer userId = createUser(user);
      User response = userDao.getUser(userId);
      walletService.addWallet(response);
      walletService.addWallet(user);
    } catch (WalletException wall) {
      assertEquals(wall.getMessage(), "Wallet account already exist for user");
      assertEquals(wall.getStatusCode(), 400);
    } catch (Exception exc) {
    }
  }

  @Test
  public void addMoneyToWalletTestNegative() throws Exception {
    try {
      user.setEmail(randomestring());
      Integer userId = createUser(user);
      User response = userDao.getUser(userId);
      Integer walletId = walletService.addWallet(user);
      walletService.addMoneyToWallet(-1.00, walletId);
      Wallet wallet = walletService.validateWallet(walletId);
      assertEquals(wallet.getId(), walletId);
      assertEquals(wallet.getBalance(), 500.00D, 0.00);
    } catch (WalletException wall) {
      assertEquals(wall.getMessage(), "Invalid Amount");
      assertEquals(wall.getStatusCode(), 400);
    }
  }

}
