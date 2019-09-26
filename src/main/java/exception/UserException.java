package exception;

public class UserException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException(int statusCode, String msg) {
		super(statusCode, msg);
	}
}