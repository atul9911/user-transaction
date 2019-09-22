package service;

import exception.WalletException;
import pojo.WalletPojo;

public interface WalletService extends BaseService {
  WalletPojo addWallet() throws WalletException;
  WalletPojo validateWallet() throws  WalletException;
  Double getWalletBalanceByWalletId(Integer id) throws Exception;
  Double getWalletBalanceByUserId(Integer userId) throws Exception;
}
