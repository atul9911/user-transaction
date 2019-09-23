package dao;

import java.util.Map;
import model.Transaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;
import utils.NullOrEmptyCheckerUtil;

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

  private void addFilterToCriteria(Criteria criteria, Map<String, ?> filters) {
    filters.keySet().forEach(k -> {
      if (!NullOrEmptyCheckerUtil.isNullOrEmpty(filters.get(k))) {
        if (k.equals("id")) {
          criteria.add(Restrictions.eq("id", filters.get(k)));
        }

        if (k.equals("afterId")) {
          criteria.add(Restrictions.ge("id", filters.get(k)));
        }

        if (k.equals("beforeId")) {
          criteria.add(Restrictions.le("id", filters.get(k)));
        }

        if (k.equals("createdBefore")) {
          criteria.add(Restrictions.le("created", filters.get(k)));
        }

        if (k.equals("createdAfter")) {
          criteria.add(Restrictions.ge("created", filters.get(k)));
        }

        if (k.equals("updatedBefore")) {
          criteria.add(Restrictions.le("updated", filters.get(k)));
        }

        if (k.equals("updatedAfter")) {
          criteria.add(Restrictions.ge("updated", filters.get(k)));
        }

        if (k.equals("status")) {
          criteria.add(Restrictions.eq("status", filters.get(k)));
        }

        if (k.equals("customerId")) {
          criteria.add(Restrictions.eq("senderUserId", filters.get(k)));
        }
      }
    });
  }

  @SuppressWarnings("unchecked")
  public TransactionDao getDaoObject() {
    return new TransactionDao();
  }
}
