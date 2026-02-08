package ovh.electricowl.bubble.services.exceptions;

public class InvalidQuestionException extends Exception {

	private static final long serialVersionUID = 4650538110757799539L;

	public InvalidQuestionException(String message) {
		super(message);
	}

	public InvalidQuestionException(String message, Throwable error) {
		super(message, error);
	}

}
