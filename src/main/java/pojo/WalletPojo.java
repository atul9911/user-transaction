package pojo;

import enums.WalletStatus;

public class WalletPojo {
    Long id;
    Long userId;
    WalletStatus walletStatus;
    Double balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WalletPojo)) return false;

        WalletPojo walletPojo = (WalletPojo) o;

        if (!getId().equals(walletPojo.getId())) return false;
        if (!getUserId().equals(walletPojo.getUserId())) return false;
        if (getWalletStatus() != walletPojo.getWalletStatus()) return false;
        return getBalance().equals(walletPojo.getBalance());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + getWalletStatus().hashCode();
        result = 31 * result + getBalance().hashCode();
        return result;
    }
}
