package ovh.electricowl.bubble.services.exceptions;

public class SessionExpiredException extends Exception {

	private static final long serialVersionUID = -1382088981305954967L;

	public SessionExpiredException(String message) {
		super(message);
	}

}
