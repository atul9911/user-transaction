package model;

import enums.TransactionStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TransactionLock", uniqueConstraints = {@UniqueConstraint(columnNames = "TRANSACTIONID")})
public class TransactionLock extends BaseModel {

  @Column(name = "TRANSACTIONID", unique = true, nullable = false, length = 100)
  private Integer transactionId;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false, length = 100)
  private TransactionStatus transactionStatus;

  public void setTransactionId(Integer transactionId) {
    this.transactionId = transactionId;
  }

  public void setTransactionStatus(TransactionStatus transactionStatus) {
    this.transactionStatus = transactionStatus;
  }
}
