package ovh.electricowl.bubble.repositories.exceptions;

public class DAONotFoundException extends Exception {

	private static final long serialVersionUID = 804556324068607285L;

	public DAONotFoundException(String message) {
		super(message);
	}

	public DAONotFoundException(String message, Throwable error) {
		super(message, error);
	}

	public DAONotFoundException(Throwable error) {
		super(error);
	}

}
