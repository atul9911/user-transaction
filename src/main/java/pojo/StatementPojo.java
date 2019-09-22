package pojo;

import enums.TransactionStatus;
import enums.TransactionType;

import java.util.Date;

public class StatementPojo {
    Long userId;
    Double amount;
    TransactionType transactionType;
    TransactionStatus transactionStatus;
    Date createdAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatementPojo)) return false;

        StatementPojo statementPojo = (StatementPojo) o;

        if (getUserId() != null ? !getUserId().equals(statementPojo.getUserId()) : statementPojo.getUserId() != null)
            return false;
        if (getAmount() != null ? !getAmount().equals(statementPojo.getAmount()) : statementPojo.getAmount() != null)
            return false;
        if (getTransactionType() != statementPojo.getTransactionType()) return false;
        if (getTransactionStatus() != statementPojo.getTransactionStatus()) return false;
        return getCreatedAt() != null ? getCreatedAt().equals(statementPojo.getCreatedAt()) : statementPojo
            .getCreatedAt() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getTransactionType() != null ? getTransactionType().hashCode() : 0);
        result = 31 * result + (getTransactionStatus() != null ? getTransactionStatus().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StatementPojo{" +
                "userId=" + userId +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                ", createdAt=" + createdAt +
                '}';
    }
}
