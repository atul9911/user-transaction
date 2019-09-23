package service.impl;

import dao.BaseDaoRegistry;
import dao.UserDao;
import dao.WalletDao;
import enums.WalletStatus;
import exception.WalletException;
import model.User;
import model.Wallet;
import service.WalletService;
import utils.NullOrEmptyCheckerUtil;

public class WallerServiceImpl implements WalletService {

  private final BaseDaoRegistry baseDaoRegistry = BaseDaoRegistry.getBaseDaoRegistry();

  private final WalletDao walletDao = baseDaoRegistry.getWalletDaoInstance();

  private final UserDao userDao = baseDaoRegistry.getUserDaoInstance();

  @Override
  public Integer addWallet(User user) throws WalletException {
    if (NullOrEmptyCheckerUtil.isNullOrEmpty(user) || NullOrEmptyCheckerUtil
        .isNullOrEmpty(userDao.getUser(user.getId()))) {
      throw new WalletException(400, "Invalid user");
    }

    Wallet existingWallet = walletDao.fetchWalletForUser(user.getId(), WalletStatus.ACTIVE);
    if (!NullOrEmptyCheckerUtil.isNullOrEmpty(existingWallet)) {
      throw new WalletException(400, "Wallet account already exist for user");
    }
    Wallet wallet = new Wallet();
    wallet.setUserId(user.getId());
    wallet.setWalletStatus(WalletStatus.ACTIVE);
    wallet.setBalance(0.00);
    return walletDao.createWallet(wallet);
  }

  @Override
  public Wallet validateWallet(Integer id) throws WalletException {
    return walletDao.getWallet(id);
  }

  @Override
  public void addMoneyToWallet(Double amount, Integer walletId) {
    Wallet wallet = walletDao.getWallet(walletId);
    if (NullOrEmptyCheckerUtil.isNullOrEmpty(wallet) || WalletStatus.INACTIVE
        .equals(wallet.getWalletStatus())) {
      throw new WalletException(404, "Wallet does not exist or inactive");
    }
    wallet.setBalance(wallet.getBalance() + amount);
    walletDao.updateWallet(wallet);
  }
}
