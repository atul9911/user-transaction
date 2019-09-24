package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import enums.UserStatus;
import enums.WalletStatus;
import model.User;
import model.Wallet;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WalletDaoTest {

  WalletDao walletDao;

  static UserDao userDao;

  static User user;

  Wallet wallet;



  private static String randomestring() {
    String generatedstring = RandomStringUtils.randomAlphabetic(8);
    return (generatedstring) + "@gmail.com";
  }

  @BeforeClass
  public static void intializeUser() {
    userDao = new UserDao();
    user = new User();
    user.setEmail(randomestring());
    user.setFirstName("abc");
    user.setLastName("xyz");
    user.setStatus(UserStatus.ACTIVE);
    user.setMobile("123456778");
    Integer id = userDao.createUser(user);
    user.setId(id);

  }

  @Before
  public void intializeWallet() {
    walletDao = new WalletDao();
    wallet = new Wallet();
    wallet.setWalletStatus(WalletStatus.ACTIVE);
    wallet.setBalance(3000.00);
    wallet.setUserId(user.getId());
  }

  @Test
  public void createWalletTest() {
    Integer walletId = walletDao.createWallet(wallet);
    assertNotNull(walletId);
  }

  @Test
  public void getWalletTest(){
    Integer walletId = walletDao.createWallet(wallet);
    Wallet resp = walletDao.getWallet(walletId);
    assertEquals(resp.getBalance(),wallet.getBalance());
    assertEquals(resp.getUserId(),wallet.getUserId());
    assertEquals(resp.getWalletStatus(),wallet.getWalletStatus());
  }

}
