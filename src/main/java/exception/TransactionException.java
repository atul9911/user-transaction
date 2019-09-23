package exception;

public class TransactionException extends BaseException {

  public TransactionException(int statusCode, String msg) {
    super(statusCode, msg);
  }
}