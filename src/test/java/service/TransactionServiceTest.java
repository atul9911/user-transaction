package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import dao.UserDao;
import dao.WalletDao;
import enums.TransactionStatus;
import enums.UserStatus;
import enums.WalletStatus;
import exception.TransactionException;
import model.User;
import model.Wallet;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import pojo.TransactionPojo;

public class TransactionServiceTest {

  private static UserDao userDao;
  private static WalletDao walletDao;
  private static User sender;
  private static User beneficiary;
  private static Wallet senderWallet;
  private static Wallet beneficiaryWallet;

  private TransactionService transactionService;

  private static String randomestring() {
    String generatedstring = RandomStringUtils.randomAlphabetic(8);
    return (generatedstring) + "@gmail.com";
  }

  @Before
  public void intializeUserAndWallet() throws Exception {
    userDao = new UserDao();
    walletDao = new WalletDao();
    sender = new User();
    beneficiary = new User();
    senderWallet = new Wallet();
    beneficiaryWallet = new Wallet();

    sender.setEmail(randomestring());
    beneficiary.setEmail(randomestring());
    sender.setFirstName("Sender");
    beneficiary.setFirstName("Beneficiary");
    sender.setMobile("98178790");
    beneficiary.setMobile("78787878");
    sender.setStatus(UserStatus.ACTIVE);
    beneficiary.setStatus(UserStatus.ACTIVE);

    Integer senderUserId = userDao.createUser(sender);
    Integer beneficiaryUserId = userDao.createUser(beneficiary);

    sender.setId(senderUserId);
    beneficiary.setId(beneficiaryUserId);

    senderWallet.setUserId(senderUserId);
    senderWallet.setBalance(5000.00);
    senderWallet.setWalletStatus(WalletStatus.ACTIVE);

    beneficiaryWallet.setUserId(beneficiaryUserId);
    beneficiaryWallet.setBalance(00.00);
    beneficiaryWallet.setWalletStatus(WalletStatus.ACTIVE);

    Integer senderWalletId = walletDao.createWallet(senderWallet);
    Integer beneficiaryWalletId = walletDao.createWallet(beneficiaryWallet);

    senderWallet.setId(senderWalletId);
    beneficiaryWallet.setId(beneficiaryWalletId);
  }

  @Before
  public void initialize() {
    transactionService = (TransactionService) BaseServiceRegistry.getService("transaction");
  }

  @Test
  public void initiateTransactionTest() {
    TransactionPojo transactionPojo = new TransactionPojo();
    transactionPojo.setSenderUserId(sender.getId());
    transactionPojo.setBeneficiaryUserId(beneficiary.getId());
    transactionPojo.setSenderWalletId(senderWallet.getId());
    transactionPojo.setBeneficiaryWalletId(beneficiaryWallet.getId());
    transactionPojo.setAmount(300.00);
    Integer transactionId = transactionService.initiateTransaction(transactionPojo);
    assertNotNull(transactionId);
  }

  @Test
  public void doTransactionTest() {
    TransactionPojo transactionPojo = new TransactionPojo();
    transactionPojo.setSenderUserId(sender.getId());
    transactionPojo.setBeneficiaryUserId(beneficiary.getId());
    transactionPojo.setSenderWalletId(senderWallet.getId());
    transactionPojo.setBeneficiaryWalletId(beneficiaryWallet.getId());
    transactionPojo.setAmount(300.00);
    Integer transactionId = transactionService.initiateTransaction(transactionPojo);
    assertNotNull(transactionId);
    executeTransaction(transactionId);
  }

  private void executeTransaction(Integer transactionId) throws TransactionException {
    TransactionPojo response = transactionService.doTransaction(transactionId);
    Wallet senderUpdatedWallet = walletDao
        .fetchWalletForUser(response.getSenderUserId(), WalletStatus.ACTIVE);
    Wallet beneficiaryUpdatedWallet = walletDao
        .fetchWalletForUser(response.getBeneficiaryUserId(), WalletStatus.ACTIVE);
    assertEquals(senderUpdatedWallet.getBalance(), senderWallet.getBalance() - 300.00, 0.00);
    assertEquals(beneficiaryUpdatedWallet.getBalance(),
        beneficiaryWallet.getBalance() + 300.00, 0.00);

    assertEquals(response.getTransactionStatus(), TransactionStatus.SUCCESS);
  }

  @Test
  public void doTransactionMultiThreadedTest() {
    TransactionPojo transactionPojo = new TransactionPojo();
    transactionPojo.setSenderUserId(sender.getId());
    transactionPojo.setBeneficiaryUserId(beneficiary.getId());
    transactionPojo.setSenderWalletId(senderWallet.getId());
    transactionPojo.setBeneficiaryWalletId(beneficiaryWallet.getId());
    transactionPojo.setAmount(300.00);
    Integer transactionId = transactionService.initiateTransaction(transactionPojo);
    assertNotNull(transactionId);

    Runnable task1 = () -> {
      try {
        executeTransaction(transactionId);
      } catch (TransactionException tex) {
        assertEquals(tex.getStatusCode(), 400);
        assertEquals(tex.getMessage(), "Transaction Already in progress");
      }
    };

    Runnable task2 = () -> {
      try {
        executeTransaction(transactionId);
      } catch (TransactionException tex) {
        assertEquals(tex.getStatusCode(), 400);
        assertEquals(tex.getMessage(), "Transaction Already in progress");
      }
    };

    Thread t1 = new Thread(task1);
    Thread t2 = new Thread(task2);
    t1.start();
    t2.start();
  }

  @Test
  public void doTransactionDuplicateTest() {
    TransactionPojo transactionPojo = new TransactionPojo();
    transactionPojo.setSenderUserId(sender.getId());
    transactionPojo.setBeneficiaryUserId(beneficiary.getId());
    transactionPojo.setSenderWalletId(senderWallet.getId());
    transactionPojo.setBeneficiaryWalletId(beneficiaryWallet.getId());
    transactionPojo.setAmount(300.00);
    Integer transactionId = transactionService.initiateTransaction(transactionPojo);
    assertNotNull(transactionId);

    try {
      executeTransaction(transactionId);
      executeTransaction(transactionId);
    } catch (TransactionException exc) {
      assertEquals(exc.getStatusCode(), 400);
      assertEquals(exc.getMessage(), "Transaction already completed");
    }
  }
}

