package ovh.electricowl.bubble.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ovh.electricowl.bubble.dto.ResultResponse;
import ovh.electricowl.bubble.services.exceptions.InvalidAnswerException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;
import ovh.electricowl.bubble.services.exceptions.TestNotCompletedException;

@Service
public interface ResultService {

	public ResultResponse retrieveResults(UUID sessionId)
			throws ServiceException, SessionNotFoundException, TestNotCompletedException,
			InvalidAnswerException, ResourceNotFoundException;

}
