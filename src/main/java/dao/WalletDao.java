package dao;

import enums.WalletStatus;
import model.Wallet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

public class WalletDao implements BaseDao {

  public Integer createWallet(Wallet wallet) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Integer id = (Integer) session.save(wallet);
    session.getTransaction().commit();
    return id;
  }

  public Wallet getWallet(Integer id) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Wallet wallet = (Wallet) session.get(Wallet.class, id);
    session.getTransaction().commit();
    return wallet;
  }

  public void updateWallet(Wallet wallet) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    session.update(wallet);
    session.getTransaction().commit();
  }

  public Wallet fetchWalletForUser(Integer userId, WalletStatus walletStatus) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria(Wallet.class);
    criteria.add(Restrictions.eq("userId", userId));
    criteria.add(Restrictions.eq("walletStatus", walletStatus));
    Wallet wallet = (Wallet) criteria.uniqueResult();
    session.getTransaction().commit();
    return wallet;
  }

  public WalletDao getDaoObject() {
    return new WalletDao();
  }
}
