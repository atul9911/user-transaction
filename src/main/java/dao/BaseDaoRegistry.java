package dao;

public final class BaseDaoRegistry {

  private static BaseDaoRegistry baseDaoRegistry;

  private BaseDaoRegistry() {
    if (baseDaoRegistry != null) {
      throw new RuntimeException(
          "Use getInstance() method to get the single instance of this class.");
    }
  }

  public UserDao getUserDaoInstance() {
    BaseDao baseDao = new UserDao();
    return ((UserDao) baseDao).getDaoObject();
  }

  public TransactionDao getTransactionDaoInstance() {
    BaseDao baseDao = new TransactionDao();
    return ((TransactionDao) baseDao).getDaoObject();
  }

  public WalletDao getWalletDaoInstance() {
    BaseDao baseDao = new WalletDao();
    return ((WalletDao) baseDao).getDaoObject();
  }

  public TransactionLockDao getTransactionLockDaoInstance() {
    BaseDao baseDao = new TransactionLockDao();
    return ((TransactionLockDao) baseDao).getDaoObject();
  }

  public synchronized static BaseDaoRegistry getBaseDaoRegistry() {
    if (baseDaoRegistry == null) {
      baseDaoRegistry = new BaseDaoRegistry();
    }
    return baseDaoRegistry;
  }
}
