package dao;

import model.Transaction;
import org.hibernate.Session;
import utils.HibernateUtil;

public class TransactionDao implements BaseDao {

  public Integer createTransaction(Transaction transaction) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Integer id = (Integer) session.save(transaction);
    session.getTransaction().commit();
    return id;
  }

  public Transaction getTransaction(Integer id) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Transaction transaction = (Transaction) session.get(Transaction.class, id);
    session.getTransaction().commit();
    return transaction;
  }

  public void updateTransaction(Transaction transaction) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    session.update(transaction);
    session.getTransaction().commit();
  }

  @SuppressWarnings("unchecked")
  public TransactionDao getDaoObject() {
    return new TransactionDao();
  }
}
