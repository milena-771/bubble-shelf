package ovh.electricowl.bubble.services;

import java.util.UUID;

import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;

public interface QuestionService {

	public QuestionResponse getQuestion(int order, UUID sessionId)
			throws ResourceNotFoundException, ServiceException;

}
