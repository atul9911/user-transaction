package service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import service.impl.TransactionServiceImpl;
import service.impl.UserServiceImpl;
import service.impl.WallerServiceImpl;

public class BaseServiceRegistry {

  private static final Map<String, BaseService> baseServiceList;

  static {
    baseServiceList = new ConcurrentHashMap<>();
    registerService();
  }

  private BaseServiceRegistry() {
  }

  private static void registerService() {
    UserService userService = new UserServiceImpl();
    WalletService walletService = new WallerServiceImpl();
    TransactionService transactionService = new TransactionServiceImpl();
    baseServiceList.put("user", userService);
    baseServiceList.put("wallet", walletService);
    baseServiceList.put("transaction", transactionService);
  }

  public synchronized static BaseService getService(String service) {
    return baseServiceList.get(service);
  }
}
