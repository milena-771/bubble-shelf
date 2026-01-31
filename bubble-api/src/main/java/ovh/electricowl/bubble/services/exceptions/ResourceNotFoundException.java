package ovh.electricowl.bubble.services.exceptions;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 6798615184159124666L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable error) {
		super(message, error);
	}

	public ResourceNotFoundException(Throwable error) {
		super(error);
	}

}
