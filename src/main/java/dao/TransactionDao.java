package dao;

import enums.TransactionStatus;
import java.util.List;
import java.util.Map;
import model.Transaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
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

  public Transaction updateTransaction(Transaction transaction) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    session.update(transaction);
    session.getTransaction().commit();
    return transaction;
  }

  public List<Transaction> getTransactionUsingFilters(Map<String, ?> filters, Integer offset,
      Integer limit, Order orderBy) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria(Transaction.class);
    addFilterToCriteria(criteria, filters);
    if (NullOrEmptyCheckerUtil.isNullOrEmpty(offset)) {
      offset = 0;
    }
    if (NullOrEmptyCheckerUtil.isNullOrEmpty(limit)) {
      limit = 100;
    }
    if (offset < 0) {
      offset = 0;
    }
    if (limit <= 0) {
      limit = 100;
    }
    if (limit > 100) {
      limit = 100;
    }
    criteria.addOrder(orderBy);
    criteria.setFirstResult(offset);
    criteria.setMaxResults(limit);
    session.getTransaction().commit();
    return criteria.list();

  }

  private void addFilterToCriteria(Criteria criteria, Map<String, ?> filters) {
    filters.keySet().stream().forEach(k -> {
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

  @Override
  public TransactionDao getDaoObject() {
    return new TransactionDao();
  }
}
