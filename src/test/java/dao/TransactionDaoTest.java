package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import enums.TransactionStatus;
import model.Transaction;
import org.junit.Before;
import org.junit.Test;

public class TransactionDaoTest {

  TransactionDao transactionDao;
  Transaction transaction;

  @Before
  public void initialize() {
    transactionDao = new TransactionDao();
    transaction = new Transaction();
    transaction.setTransactionStatus(TransactionStatus.PENDING);
    transaction.setSenderWalletId(123);
    transaction.setBeneficiaryWalletId(1234);
    transaction.setSenderUserId(123);
    transaction.setBeneficiaryUserId(1234);
    transaction.setAmount(300.00);
  }

  @Test
  public void createTransactionTest() {
    Integer transactionId = transactionDao.createTransaction(transaction);
    assertNotNull(transactionId);
  }

  @Test
  public void getTransactionTest() {
    Integer transactionId = transactionDao.createTransaction(transaction);
    assertNotNull(transactionId);
    Transaction response = transactionDao.getTransaction(transactionId);
    assertEquals(response.getAmount(), transaction.getAmount());
    assertEquals(response.getBeneficiaryUserId(), transaction.getBeneficiaryUserId());
    assertEquals(response.getBeneficiaryWalletId(), transaction.getBeneficiaryWalletId());
    assertEquals(response.getSenderUserId(), transaction.getSenderUserId());
    assertEquals(response.getSenderWalletId(), transaction.getSenderWalletId());
    assertEquals(response.getTransactionStatus(), transaction.getTransactionStatus());
  }

  @Test
  public void updateTransactionTest() {
    Integer transactionId = transactionDao.createTransaction(transaction);
    transaction.setTransactionStatus(TransactionStatus.SUCCESS);
    transactionDao.updateTransaction(transaction);
    Transaction response = transactionDao.getTransaction(transactionId);
    assertEquals(response.getAmount(), transaction.getAmount());
    assertEquals(response.getBeneficiaryUserId(), transaction.getBeneficiaryUserId());
    assertEquals(response.getBeneficiaryWalletId(), transaction.getBeneficiaryWalletId());
    assertEquals(response.getSenderUserId(), transaction.getSenderUserId());
    assertEquals(response.getSenderWalletId(), transaction.getSenderWalletId());
    assertEquals(response.getTransactionStatus(), TransactionStatus.SUCCESS);
  }

}
