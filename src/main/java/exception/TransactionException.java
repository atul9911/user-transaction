package exception;

public class TransactionException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionException(int statusCode, String msg) {
		super(statusCode, msg);
	}
}