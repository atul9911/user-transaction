package dao;

import org.hibernate.Session;

import enums.TransactionStatus;
import model.TransactionLock;
import utils.HibernateUtil;

public class TransactionLockDao implements BaseDao {

  public void acquireLock(Integer transactionId, TransactionStatus status) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    TransactionLock transactionLock = new TransactionLock();
    transactionLock.setTransactionId(transactionId);
    transactionLock.setTransactionStatus(status);
    session.save(transactionLock);
    session.getTransaction().commit();
  }

  public TransactionLockDao getDaoObject() {
    return new TransactionLockDao();
  }
}
