package ovh.electricowl.bubble.services;

import java.time.Clock;
import java.time.Duration;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.dto.SubmitAnswerRequest;
import ovh.electricowl.bubble.entities.Session;
import ovh.electricowl.bubble.repositories.SessionRepository;
import ovh.electricowl.bubble.services.exceptions.AnswerAlreadySubmittedException;
import ovh.electricowl.bubble.services.exceptions.InvalidAnswerException;
import ovh.electricowl.bubble.services.exceptions.InvalidQuestionException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;
import ovh.electricowl.bubble.services.exceptions.SessionExpiredException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;
import ovh.electricowl.bubble.services.exceptions.TestAlreadyCompletedException;

@Service
public class AnswerServiceImpl implements AnswerService {

	private static final Logger LOG = LogManager.getLogger(AnswerServiceImpl.class);

	@Value("${bubble.total.questions.number}")
	private Integer totalQuestions;

	private SessionRepository sessionRepository;

	private QuestionService questionService;

	private final Duration maxTimePerQuestion;

	private final Clock clock;

	public AnswerServiceImpl(SessionRepository sessionRepository, QuestionService questionService,
			@Value("${test.session.max-time-per-question}") Duration maxTimePerQuestion,
			Clock clock) {
		this.sessionRepository = sessionRepository;
		this.questionService = questionService;
		this.maxTimePerQuestion = maxTimePerQuestion;
		this.clock = clock;
	}

	@Override
	public QuestionResponse submitAnswer(SubmitAnswerRequest request, UUID sessionId)
			throws SessionNotFoundException, SessionExpiredException, ResourceNotFoundException,
			TestAlreadyCompletedException, AnswerAlreadySubmittedException,
			InvalidQuestionException, InvalidAnswerException, ServiceException {
		try {
			LOG.info("START >>> submitAnswer");
			QuestionResponse response = null;
			if (sessionId == null) {
				throw new ServiceException("sessionId is required.");
			}
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(SessionNotFoundException::new);
			session.validateAnswerTime();
			Integer questionOrder = request.getQuestionOrder();
			Long answerId = request.getAnswerId();
			if (questionOrder != session.getCurrentQuestionNumber()) {
				LOG.warn("Wrong current question number.");
				throw new InvalidQuestionException("Wrong current question number.");
			}
			if (!questionService.checkValidAnswer(questionOrder, answerId)) {
				LOG.warn("Wrong answer.");
				throw new InvalidAnswerException("Wrong answer.");
			}
			session.addAnswer(questionOrder, answerId);

			questionOrder++;
			if (questionOrder >= totalQuestions) {
				// --> Handle the end of the test
			} else {
				session.moveToNextQuestion(questionOrder);
				response = questionService.getQuestion(questionOrder, sessionId);
			}
			return response;
		} finally {
			LOG.info("END <<< submitAnswer");
		}
	}

}
