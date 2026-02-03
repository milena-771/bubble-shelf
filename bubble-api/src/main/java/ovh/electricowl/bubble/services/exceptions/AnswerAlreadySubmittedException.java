package ovh.electricowl.bubble.services.exceptions;

public class AnswerAlreadySubmittedException extends Exception {

	private static final long serialVersionUID = 7084884870295204934L;

	public AnswerAlreadySubmittedException(String message) {
		super(message);
	}

}
