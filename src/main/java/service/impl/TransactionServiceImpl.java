package service.impl;

import dao.BaseDaoRegistry;
import dao.TransactionDao;
import dao.TransactionLockDao;
import dao.UserDao;
import dao.WalletDao;
import enums.TransactionStatus;
import enums.UserStatus;
import exception.TransactionException;
import model.Transaction;
import model.User;
import model.Wallet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import pojo.TransactionPojo;
import service.TransactionService;
import utils.HibernateUtil;
import utils.NullOrEmptyCheckerUtil;

public class TransactionServiceImpl implements TransactionService {

  private final BaseDaoRegistry baseDaoRegistry = BaseDaoRegistry.getBaseDaoRegistry();

  private final TransactionDao transactionDao = baseDaoRegistry.getTransactionDaoInstance();

  private final UserDao userDao = baseDaoRegistry.getUserDaoInstance();

  private final WalletDao walletDao = baseDaoRegistry.getWalletDaoInstance();

  private final TransactionLockDao transactionLockDao = baseDaoRegistry
      .getTransactionLockDaoInstance();

  @Override
  public Integer initiateTransaction(TransactionPojo transactionPojo)
      throws TransactionException {
    validateTransactionRequest(transactionPojo);
    validateBeneficiaryAndSender(transactionPojo);
    return generateTransactionId(transactionPojo);
  }

  private Integer generateTransactionId(TransactionPojo transactionPojo)
      throws TransactionException {
    Transaction transaction = new Transaction();
    transaction.setAmount(transactionPojo.getAmount());
    transaction.setBeneficiaryUserId(transactionPojo.getBeneficiaryUserId());
    transaction.setSenderUserId(transactionPojo.getSenderUserId());
    transaction.setBeneficiaryWalletId(transactionPojo.getBeneficiaryWalletId());
    transaction.setSenderWalletId(transactionPojo.getSenderWalletId());
    transaction.setTransactionStatus(TransactionStatus.PENDING);
    return transactionDao.createTransaction(transaction);
  }

  private void validateBeneficiaryAndSender(TransactionPojo transactionPojo)
      throws TransactionException {
    User beneficiary = userDao.getUser(transactionPojo.getBeneficiaryUserId());
    if (NullOrEmptyCheckerUtil.isNullOrEmpty(beneficiary) || UserStatus.INACTIVE
        .equals(beneficiary.getStatus())) {
      throw new TransactionException(404, "Beneficiary not active or not exist");
    }

    User sender = userDao.getUser(transactionPojo.getSenderUserId());
    if (NullOrEmptyCheckerUtil.isNullOrEmpty(sender) || UserStatus.INACTIVE
        .equals(sender.getStatus())) {
      throw new TransactionException(404, "Invalid Sender or not active");
    }

    if (NullOrEmptyCheckerUtil
        .isNullOrEmpty(walletDao.getWallet(transactionPojo.getBeneficiaryWalletId()))) {
      throw new TransactionException(404, "Invalid Beneficiary Wallet");
    }

    Wallet senderWallet = walletDao.getWallet(transactionPojo.getSenderWalletId());
    if (NullOrEmptyCheckerUtil.isNullOrEmpty(senderWallet)) {
      throw new TransactionException(404, "Invalid Sender wallet");
    }

    if (senderWallet.getBalance() < transactionPojo.getAmount()) {
      throw new TransactionException(400, "Insufficient Balance in sender wallet account");
    }
  }

  private void validateTransactionRequest(TransactionPojo transactionPojo)
      throws TransactionException {
    if (NullOrEmptyCheckerUtil.isNullOrEmpty(transactionPojo.getBeneficiaryUserId())) {
      throw new TransactionException(400, "Invalid beneficiary id");
    }

    if (NullOrEmptyCheckerUtil.isNullOrEmpty(transactionPojo.getBeneficiaryWalletId())) {
      throw new TransactionException(400, "Invalid beneficiary wallet id");
    }

    if (NullOrEmptyCheckerUtil.isNullOrEmpty(transactionPojo.getSenderUserId())) {
      throw new TransactionException(400, "Invalid sender id");
    }

    if (NullOrEmptyCheckerUtil.isNullOrEmpty(transactionPojo.getSenderWalletId())) {
      throw new TransactionException(400, "Invalid sender wallet id");
    }

    if (NullOrEmptyCheckerUtil.isNullOrEmpty(transactionPojo.getAmount())
        || transactionPojo.getAmount() < 1) {
      throw new TransactionException(400, "Invalid amount or amount must be greater than 0");
    }
  }

  @Override
  public TransactionPojo doTransaction(Integer transactionId)
      throws TransactionException {
    Transaction transaction = null;
    try {
      transaction = transactionDao.getTransaction(transactionId);
      if (NullOrEmptyCheckerUtil.isNullOrEmpty(transaction)) {
        throw new TransactionException(404, "Transaction not found");
      }

      if (!TransactionStatus.PENDING.equals(transaction.getTransactionStatus())) {
        throw new TransactionException(400, "Transaction already completed");
      }

      transactionLockDao.acquireLock(transactionId, TransactionStatus.SUCCESS);
      Wallet senderWallet = walletDao.getWallet(transaction.getSenderWalletId());
      Wallet beneficiaryWallet = walletDao.getWallet(transaction.getBeneficiaryWalletId());
      senderWallet.setBalance(senderWallet.getBalance() - transaction.getAmount());
      beneficiaryWallet.setBalance(beneficiaryWallet.getBalance() + transaction.getAmount());
      transaction.setTransactionStatus(TransactionStatus.SUCCESS);
      updateBalancesAndCommitTransaction(beneficiaryWallet, senderWallet, transaction);
      TransactionPojo transactionPojo = new TransactionPojo();
      transactionPojo.setAmount(transaction.getAmount());
      transactionPojo.setBeneficiaryUserId(transaction.getBeneficiaryUserId());
      transactionPojo.setSenderUserId(transaction.getSenderUserId());
      transactionPojo.setTransactionStatus(TransactionStatus.SUCCESS);
      return transactionPojo;
    } catch (ConstraintViolationException e) {
      System.out.println("Transaction Already processing");
      throw new TransactionException(400, "Transaction Already in progress");
    } catch (TransactionException e) {
      throw e;
    } catch (Exception exc) {
      if (!NullOrEmptyCheckerUtil.isNullOrEmpty(transaction)) {
        transaction.setTransactionStatus(TransactionStatus.FAIL);
        transactionDao.updateTransaction(transaction);
      }
      throw new TransactionException(500, "Unable to update Transaction");
    }
  }

  private void updateBalancesAndCommitTransaction(Wallet beneficiary, Wallet sender,
      Transaction transaction) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.update(beneficiary);
    session.update(sender);
    session.update(transaction);
    Query q = session.createQuery("delete from TransactionLock where TRANSACTIONID=:transactionId");
    q.setParameter("transactionId", transaction.getId());
    session.getTransaction().commit();
  }

}
