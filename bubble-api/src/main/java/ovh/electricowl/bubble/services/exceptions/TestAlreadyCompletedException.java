package ovh.electricowl.bubble.services.exceptions;

public class TestAlreadyCompletedException extends Exception {

	private static final long serialVersionUID = -5687819860074258790L;

	public TestAlreadyCompletedException(String message) {
		super(message);
	}
}
