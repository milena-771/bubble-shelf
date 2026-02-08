package ovh.electricowl.bubble.services.exceptions;

public class InvalidAnswerException extends Exception {

	private static final long serialVersionUID = 3303055515936361025L;

	public InvalidAnswerException(String message) {
		super(message);
	}

	public InvalidAnswerException(String message, Throwable error) {
		super(message, error);
	}
}
