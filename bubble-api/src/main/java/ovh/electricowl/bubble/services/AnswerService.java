package ovh.electricowl.bubble.services;

import java.util.UUID;

import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.dto.SubmitAnswerRequest;
import ovh.electricowl.bubble.services.exceptions.AnswerAlreadySubmittedException;
import ovh.electricowl.bubble.services.exceptions.InvalidAnswerException;
import ovh.electricowl.bubble.services.exceptions.InvalidQuestionException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;
import ovh.electricowl.bubble.services.exceptions.SessionExpiredException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;
import ovh.electricowl.bubble.services.exceptions.TestAlreadyCompletedException;

public interface AnswerService {

	public QuestionResponse submitAnswer(SubmitAnswerRequest request, UUID session)
			throws SessionNotFoundException, SessionExpiredException, ResourceNotFoundException,
			ServiceException, TestAlreadyCompletedException, AnswerAlreadySubmittedException,
			InvalidQuestionException, InvalidAnswerException;

}
