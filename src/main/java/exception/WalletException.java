package exception;

public class WalletException extends BaseException {

  public WalletException(int statusCode, String msg) {
    super(statusCode, msg);
  }
}