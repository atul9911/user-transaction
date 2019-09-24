package model;

import enums.WalletStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "Wallet")
public class Wallet extends BaseModel {
  @Column(name = "USERID", unique = false, nullable = false, length = 100)
  private Integer userId;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false, length = 100)
  private WalletStatus walletStatus;

  @Column(name = "BALANCE", unique = false, nullable = false, length = 100)
  private Double balance;


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public WalletStatus getWalletStatus() {
    return walletStatus;
  }

  public void setWalletStatus(WalletStatus walletStatus) {
    this.walletStatus = walletStatus;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }
}
