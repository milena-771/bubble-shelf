package ovh.electricowl.bubble.repositories.exceptions;

public class DuplicateResourceException extends Exception {

	private static final long serialVersionUID = 3295091559946979713L;

	public DuplicateResourceException(String message) {
		super(message);
	}

	public DuplicateResourceException(Throwable e) {
		super(e);
	}

}
