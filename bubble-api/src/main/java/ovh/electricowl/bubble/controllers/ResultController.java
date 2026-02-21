package ovh.electricowl.bubble.controllers;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ovh.electricowl.bubble.dto.ResultResponse;
import ovh.electricowl.bubble.services.ResultService;
import ovh.electricowl.bubble.services.exceptions.InvalidAnswerException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;
import ovh.electricowl.bubble.services.exceptions.TestNotCompletedException;

@RestController
@RequestMapping("/results")
public class ResultController {

	private static final Logger LOG = LogManager.getLogger(ResultController.class);

	private ResultService resultService;

	public ResultController(ResultService resultService) {
		this.resultService = resultService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResultResponse getResults(
			@RequestHeader(value = "X-Test-Session-Id", required = false) UUID sessionId)
			throws ServiceException, SessionNotFoundException, TestNotCompletedException,
			InvalidAnswerException, ResourceNotFoundException {
		try {
			LOG.info("START >>> getResults");
			return resultService.retrieveResults(sessionId);
		} finally {
			LOG.info("END <<< getResults");
		}
	}

}
