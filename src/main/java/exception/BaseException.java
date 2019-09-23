package exception;


public class BaseException extends RuntimeException {

  private final int statusCode;

  BaseException() {
    super();
    this.statusCode = 500;
  }

  BaseException(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }


}

