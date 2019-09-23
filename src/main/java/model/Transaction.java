package model;

import enums.TransactionStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction extends BaseModel {

  @Column(name = "SENDERWALLETID", unique = false, nullable = false, length = 100)
  private Integer senderWalletId;

  @Column(name = "BENEFICIARYWALLETID", unique = false, nullable = false, length = 100)
  private Integer beneficiaryWalletId;

  @Column(name = "SENDERID", unique = false, nullable = false, length = 100)
  private Integer senderUserId;

  @Column(name = "BENEFICIARYID", unique = false, nullable = false, length = 100)
  private Integer beneficiaryUserId;

  @Column(name = "AMOUNT", unique = false, nullable = false, length = 100)
  private Double amount;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", unique = false, nullable = false, length = 100)
  private TransactionStatus transactionStatus;

  public Integer getSenderWalletId() {
    return senderWalletId;
  }

  public void setSenderWalletId(Integer senderWalletId) {
    this.senderWalletId = senderWalletId;
  }

  public Integer getBeneficiaryWalletId() {
    return beneficiaryWalletId;
  }

  public void setBeneficiaryWalletId(Integer beneficiaryWalletId) {
    this.beneficiaryWalletId = beneficiaryWalletId;
  }

  public Integer getSenderUserId() {
    return senderUserId;
  }

  public void setSenderUserId(Integer senderUserId) {
    this.senderUserId = senderUserId;
  }

  public Integer getBeneficiaryUserId() {
    return beneficiaryUserId;
  }

  public void setBeneficiaryUserId(Integer beneficiaryUserId) {
    this.beneficiaryUserId = beneficiaryUserId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public TransactionStatus getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(TransactionStatus transactionStatus) {
    this.transactionStatus = transactionStatus;
  }
}
