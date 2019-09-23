package dao;

import enums.TransactionStatus;
import java.sql.SQLException;
import model.TransactionLock;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class TransactionLockDao implements BaseDao {

  public void acquireLock(Integer transactionId, TransactionStatus status) throws SQLException {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    TransactionLock transactionLock = new TransactionLock();
    transactionLock.setTransactionId(transactionId);
    transactionLock.setTransactionStatus(status);
    session.save(transactionLock);
    session.getTransaction().commit();
  }

  public void releaseLock(Integer transactionId) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Query q = session.createQuery("delete from TransactionLock where TRANSACTIONID=:transactionId");
    q.setParameter("transactionId", transactionId);
    session.getTransaction().commit();
  }

  @Override
  public TransactionLockDao getDaoObject() {
    return new TransactionLockDao();
  }
}
