package pojo;

import enums.TransactionStatus;

import java.util.Date;

public class TransactionPojo {
    private Integer senderWalletId;
    private Integer beneficiaryWalletId;
    private Integer senderUserId;
    private Integer beneficiaryUserId;
    private Double amount;
    private TransactionStatus transactionStatus;
    private Date createdAt;

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

    private Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionPojo)) return false;

        TransactionPojo that = (TransactionPojo) o;

        if (getSenderWalletId() != null ? !getSenderWalletId().equals(that.getSenderWalletId()) : that.getSenderWalletId() != null)
            return false;
        if (getBeneficiaryWalletId() != null ? !getBeneficiaryWalletId().equals(that.getBeneficiaryWalletId()) : that.getBeneficiaryWalletId() != null)
            return false;
        if (getSenderUserId() != null ? !getSenderUserId().equals(that.getSenderUserId()) : that.getSenderUserId() != null)
            return false;
        if (getBeneficiaryUserId() != null ? !getBeneficiaryUserId().equals(that.getBeneficiaryUserId()) : that.getBeneficiaryUserId() != null)
            return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        if (getTransactionStatus() != that.getTransactionStatus()) return false;
        return getCreatedAt() != null ? getCreatedAt().equals(that.getCreatedAt()) : that.getCreatedAt() == null;
    }

    @Override
    public int hashCode() {
        int result = getSenderWalletId() != null ? getSenderWalletId().hashCode() : 0;
        result = 31 * result + (getBeneficiaryWalletId() != null ? getBeneficiaryWalletId().hashCode() : 0);
        result = 31 * result + (getSenderUserId() != null ? getSenderUserId().hashCode() : 0);
        result = 31 * result + (getBeneficiaryUserId() != null ? getBeneficiaryUserId().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getTransactionStatus() != null ? getTransactionStatus().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionPojo{" +
                "senderWalletId=" + senderWalletId +
                ", beneficiaryWalletId=" + beneficiaryWalletId +
                ", senderUserId=" + senderUserId +
                ", beneficiaryUserId=" + beneficiaryUserId +
                ", amount=" + amount +
                ", transactionStatus=" + transactionStatus +
                ", createdAt=" + createdAt +
                '}';
    }
}
