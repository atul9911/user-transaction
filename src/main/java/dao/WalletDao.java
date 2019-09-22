package dao;

import model.Wallet;
import org.hibernate.Session;
import utils.HibernateUtil;

public class WalletDao implements BaseDaoService {

  public Integer createWallet(Wallet wallet) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Integer id = (Integer) session.save(wallet);
    session.getTransaction().commit();
    return id;
  }

  public Wallet getUser(Integer id) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    Wallet wallet = (Wallet) session.get(Wallet.class, id);
    session.getTransaction().commit();
    return wallet;
  }

  @Override
  public WalletDao getDaoObject() {
    return new WalletDao();
  }
}
