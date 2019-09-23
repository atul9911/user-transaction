package exception;

public class UserException extends BaseException {

  public UserException(int statusCode, String msg) {
    super(statusCode, msg);
  }
}