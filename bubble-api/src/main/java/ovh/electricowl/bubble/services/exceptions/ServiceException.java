package ovh.electricowl.bubble.services.exceptions;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 3629107153699981928L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable error) {
		super(message, error);
	}

	public ServiceException(Throwable error) {
		super(error);
	}

}
