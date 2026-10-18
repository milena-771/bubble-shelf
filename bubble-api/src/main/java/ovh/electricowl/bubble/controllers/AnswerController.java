package ovh.electricowl.bubble.controllers;

import java.util.Locale;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.dto.SubmitAnswerRequest;
import ovh.electricowl.bubble.entities.AcceptLangEnum;
import ovh.electricowl.bubble.services.AnswerService;
import ovh.electricowl.bubble.services.exceptions.InvalidAnswerException;
import ovh.electricowl.bubble.services.exceptions.InvalidQuestionException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;
import ovh.electricowl.bubble.services.exceptions.SessionExpiredException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;
import ovh.electricowl.bubble.services.exceptions.TestAlreadyCompletedException;

@RestController
@RequestMapping("/answer")
public class AnswerController {

	private static final Logger LOG = LogManager.getLogger(AnswerController.class);

	private AnswerService answerService;

	public AnswerController(AnswerService answerService) {
		this.answerService = answerService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public QuestionResponse submitAnswer(@RequestBody @Valid SubmitAnswerRequest request,
			@RequestHeader(value = "X-Test-Session-Id", required = false) UUID sessionId,
			Locale locale) throws SessionNotFoundException, SessionExpiredException,
			ResourceNotFoundException, ServiceException, TestAlreadyCompletedException,
			InvalidQuestionException, InvalidAnswerException {
		try {
			LOG.info("START >>> submitAnswer");
			if ((locale == null)
					|| ((locale.getLanguage() == null) || locale.getLanguage().isBlank())
					|| (!AcceptLangEnum.isValidLanguage(locale.getLanguage().toString()))) {
				LOG.warn("set default to english");
				locale = Locale.ENGLISH;
			}
			return answerService.submitAnswer(request, sessionId, locale);
		} finally {
			LOG.info("END <<< submitAnswer");
		}

	}

}
