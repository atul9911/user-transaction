package pojo;

import enums.WalletStatus;

public class Wallet {
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
        if (!(o instanceof Wallet)) return false;

        Wallet wallet = (Wallet) o;

        if (!getId().equals(wallet.getId())) return false;
        if (!getUserId().equals(wallet.getUserId())) return false;
        if (getWalletStatus() != wallet.getWalletStatus()) return false;
        return getBalance().equals(wallet.getBalance());
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
