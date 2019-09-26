package exception;

public class WalletException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WalletException(int statusCode, String msg) {
		super(statusCode, msg);
	}
}