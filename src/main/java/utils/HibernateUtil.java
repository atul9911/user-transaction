package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
  private static SessionFactory sessionFactory = buildSessionFactory();

  private synchronized static SessionFactory buildSessionFactory()
  {
    try
    {
      if (sessionFactory == null)
      {
        Configuration configuration = new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      }
      return sessionFactory;
    } catch (Throwable ex)
    {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public synchronized static SessionFactory getSessionFactory()
  {
    if(sessionFactory == null){
      sessionFactory = buildSessionFactory();
    }

    return sessionFactory;
  }

  public synchronized static void shutdown()
  {
    getSessionFactory().close();
  }
}
