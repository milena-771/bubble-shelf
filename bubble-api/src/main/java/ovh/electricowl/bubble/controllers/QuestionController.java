package ovh.electricowl.bubble.controllers;

import java.util.Locale;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.entities.AcceptLangEnum;
import ovh.electricowl.bubble.services.QuestionService;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;
import ovh.electricowl.bubble.services.exceptions.SessionExpiredException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	private static final Logger LOG = LogManager.getLogger(QuestionController.class);

	private QuestionService questionService;

	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}

	@GetMapping("{num}")
	@ResponseStatus(HttpStatus.OK)
	public QuestionResponse getQuestionByNumber(@PathVariable("num") int order,
			@RequestHeader(value = "X-Test-Session-Id", required = false) UUID sessionId,
			Locale locale) throws ResourceNotFoundException, ServiceException,
			SessionNotFoundException, SessionExpiredException {
		try {
			LOG.info("START >>> getQuestionByNumber");
			if ((locale == null)
					|| ((locale.getLanguage() == null) || locale.getLanguage().isBlank())
					|| (!locale.getLanguage().toString().equals("en")
							&& !locale.getLanguage().toString().equals("fr"))) {
				LOG.warn("locale == null set default to english");
				locale = Locale.ENGLISH;
			}
			return questionService.getQuestion(order, sessionId, locale);
		} finally {
			LOG.info("END <<< getQuestionByNumber");
		}
	}

	@GetMapping("/refresh/{num}")
	@ResponseStatus(HttpStatus.OK)
	public QuestionResponse getRefreshQuestionByLang(@PathVariable("num") int order,
			@RequestHeader(value = "X-Test-Session-Id", required = false) UUID sessionId,
			Locale locale) throws ResourceNotFoundException, ServiceException,
			SessionNotFoundException, SessionExpiredException {
		try {
			LOG.info("START >>> getQuestionByNumber");
			if ((locale == null)
					|| ((locale.getLanguage() == null) || locale.getLanguage().isBlank())
					|| (!locale.getLanguage().toString().equals("en")
							&& !locale.getLanguage().toString().equals("fr"))) {
				LOG.warn("locale == null set default to english");
				locale = Locale.ENGLISH;
			}
			return questionService.getQuestionByLang(order, sessionId, locale);
		} finally {
			LOG.info("END <<< getQuestionByNumber");
		}
	}

	@GetMapping("/restart")
	@ResponseStatus(HttpStatus.OK)
	public QuestionResponse restart(
			@RequestHeader(value = "X-Test-Session-Id", required = true) UUID sessionId,
			Locale locale) throws ServiceException, SessionNotFoundException,
			ResourceNotFoundException, SessionExpiredException {
		try {
			LOG.info("START >>> restart");
			if ((locale == null)
					|| ((locale.getLanguage() == null) || locale.getLanguage().isBlank())
					|| (!AcceptLangEnum.isValidLanguage(locale.getLanguage().toString()))) {
				LOG.warn("set default to english");
				locale = Locale.ENGLISH;
			}
			return questionService.reinitSessionAndGetFirstQuestion(sessionId, locale);
		} finally {
			LOG.info("END <<< restart");
		}
	}

}
