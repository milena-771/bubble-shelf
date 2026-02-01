package ovh.electricowl.bubble.services;

import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;

public interface QuestionService {

	public QuestionResponse getQuestion(int order)
			throws ResourceNotFoundException, ServiceException;

}
