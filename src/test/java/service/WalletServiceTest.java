package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import dao.UserDao;
import enums.UserStatus;
import model.User;
import model.Wallet;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WalletServiceTest {

  static UserDao userDao;

  WalletService walletService;

  static User walletUser;

  static Integer userId;

  static User user;

  private static Integer createUser(User user) throws Exception {
    return userDao.createUser(user);
  }

  private String randomestring() {
    String generatedstring = RandomStringUtils.randomAlphabetic(8);
    return (generatedstring) + "@gmail.com";
  }

  @BeforeClass
  public static void intializeUser() throws Exception {
    userDao = new UserDao();
    user = new User();
    user.setEmail("abc@test.com");
    user.setFirstName("Test");
    user.setLastName("Test");
    user.setMobile("981998898");
    user.setStatus(UserStatus.ACTIVE);
    userId = createUser(user);
    walletUser = userDao.getUser(userId);
  }

  @Before
  public void initialize() {
    walletService = (WalletService) BaseServiceRegistry.getService("wallet");
  }

  @Test
  public void createWalletTest() {
    Integer id = walletService.addWallet(walletUser);
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
  public void addMoneyToWalletTest() throws Exception{
    Integer walletId = walletService.addWallet(user);
    walletService.addMoneyToWallet(500.00,walletId);
    Wallet wallet = walletService.validateWallet(walletId);
    assertEquals(wallet.getId(),walletId);
    assertEquals(Double.valueOf(wallet.getBalance()),500.00D,0.00);

  }

}
