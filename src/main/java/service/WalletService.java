package service;

import exception.WalletException;
import model.User;
import model.Wallet;

public interface WalletService extends BaseService {

  Integer addWallet(User user) throws WalletException;

  Wallet validateWallet(Integer id) throws WalletException;

  void addMoneyToWallet(Double amount, Integer walletId);
}
