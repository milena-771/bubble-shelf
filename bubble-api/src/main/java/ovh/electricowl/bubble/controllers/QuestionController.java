package ovh.electricowl.bubble.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.services.QuestionService;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;

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
	public QuestionResponse getQuestionByNumber(@PathVariable("num") int order)
			throws ResourceNotFoundException, ServiceException {
		try {
			LOG.info("START >>> getQuestionByNumber");
			return questionService.getQuestion(order);
		} finally {
			LOG.info("END <<< getQuestionByNumber");
		}
	}

}
