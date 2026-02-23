package ovh.electricowl.bubble.services;

import java.util.UUID;

import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;

public interface QuestionService {

	public QuestionResponse getQuestion(int order, UUID sessionId)
			throws ResourceNotFoundException, ServiceException;

	public boolean checkValidAnswer(Integer questionOrder, Long answerId)
			throws ResourceNotFoundException, ServiceException;

	public QuestionResponse reinitSessionAndGetFirstQuestion(UUID sessionId)
			throws ServiceException, SessionNotFoundException, ResourceNotFoundException;

}
