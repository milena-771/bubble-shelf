package ovh.electricowl.bubble.repositories.exceptions;

public class DAOException extends Exception {

	private static final long serialVersionUID = 2845936906147493803L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable error) {
		super(message, error);
	}

	public DAOException(Throwable error) {
		super(error);
	}

}
