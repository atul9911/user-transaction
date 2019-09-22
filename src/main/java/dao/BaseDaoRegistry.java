package dao;

public final class BaseDaoRegistry {
  private static BaseDaoRegistry baseDaoRegistry;

  private BaseDaoService baseDao;

  private BaseDaoRegistry() {
    if (baseDaoRegistry != null){
      throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
    }
  }

  public UserDao getUserDaoInstance() {
    baseDao = new UserDao();
    return ((UserDao) baseDao).getDaoObject();
  }

  public synchronized static BaseDaoRegistry getBaseDaoRegistry() {
    if(baseDaoRegistry == null){
      baseDaoRegistry= new BaseDaoRegistry();
    }
    return baseDaoRegistry;
  }
}
