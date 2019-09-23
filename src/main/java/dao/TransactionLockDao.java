package dao;

import enums.TransactionStatus;
import java.sql.SQLException;
import model.TransactionLock;
import org.hibernate.Query;
import org.hibernate.Session;
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

  @SuppressWarnings("unchecked")
  public TransactionLockDao getDaoObject() {
    return new TransactionLockDao();
  }
}
