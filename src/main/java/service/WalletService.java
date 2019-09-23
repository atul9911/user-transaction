package service;

import exception.WalletException;
import model.User;
import model.Wallet;
import pojo.WalletPojo;

public interface WalletService extends BaseService {

  Integer addWallet(User user) throws WalletException;

  Wallet validateWallet(Integer id) throws WalletException;

  Double getWalletBalanceByWalletId(Integer id) throws WalletException;

  Double getWalletBalanceByUserId(Integer userId) throws WalletException;

  void addMoneyToWallet(Double amount, Integer walletId) throws Exception;
}
