package ovh.electricowl.bubble.services;

import java.time.Clock;
import java.time.Duration;
import java.util.Locale;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.entities.Question;
import ovh.electricowl.bubble.entities.Session;
import ovh.electricowl.bubble.mapper.QuestionMapper;
import ovh.electricowl.bubble.repositories.QuestionRepository;
import ovh.electricowl.bubble.repositories.SessionRepository;
import ovh.electricowl.bubble.repositories.exceptions.DAOException;
import ovh.electricowl.bubble.repositories.exceptions.DAONotFoundException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;
import ovh.electricowl.bubble.services.exceptions.SessionExpiredException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final Logger LOG = LogManager.getLogger(QuestionServiceImpl.class);

	@Value("${bubble.total.questions.number}")
	private Integer totalQuestions;

	private QuestionRepository questionRepository;

	private SessionRepository sessionRepository;

	private final Duration maxTimePerQuestion;

	private final Clock clock;

	public QuestionServiceImpl(QuestionRepository questionRepository,
			SessionRepository sessionRepository,
			@Value("${test.session.max-time-per-question}") Duration maxTimePerQuestion,
			Clock clock) {
		this.questionRepository = questionRepository;
		this.sessionRepository = sessionRepository;
		this.maxTimePerQuestion = maxTimePerQuestion;
		this.clock = clock;
	}

	@Override
	public QuestionResponse getQuestion(int order, UUID sessionId, Locale locale)
			throws ResourceNotFoundException, ServiceException, SessionNotFoundException,
			SessionExpiredException {
		try {
			LOG.info("START >>> getQuestion");

			Session session;

			Question entity = questionRepository.findQuestionByOrder(order, locale);

			if (entity == null) {
				LOG.warn("No question found : {} {}", order, locale.getLanguage());
				throw new ResourceNotFoundException("No question found");
			}

			if ((order == 1) && (sessionId == null)) {
				session = new Session(entity.getOrder(), maxTimePerQuestion, clock);
				sessionRepository.save(session);
			} else {
				if (sessionId == null) {
					LOG.warn("sessionId is required for next questions.");
					throw new ServiceException("sessionId is required for next questions.");
				}
				session = sessionRepository.findById(sessionId)
						.orElseThrow(SessionNotFoundException::new);
				session.validateAnswerTime();
			}
			QuestionResponse response = QuestionMapper.toQuestionResponse(entity, totalQuestions);
			Long idAnswer = session.getAnswers().getOrDefault(order, 0L);
			response.setSelectedAnswer(idAnswer);
			session.startQuestionTimer();
			sessionRepository.save(session);
			response.setSessionId(session.getId());
			return response;
		} catch (DAONotFoundException e) {
			LOG.warn("No data found : {}", e.getMessage());
			throw new ResourceNotFoundException(e);
		} catch (DAOException e) {
			LOG.warn("Err getQuestion() --> {}", e);
			throw new ServiceException(e);
		} finally {
			LOG.info("END <<< getQuestion");
		}
	}

	@Override
	public QuestionResponse getQuestionByLang(int order, UUID sessionId, Locale locale)
			throws ResourceNotFoundException, ServiceException, SessionNotFoundException,
			SessionExpiredException {
		try {
			LOG.info("START >>> getQuestionByLang");
			if (sessionId == null) {
				LOG.warn("sessionId is required for next questions.");
				throw new ServiceException("sessionId is required for next questions.");
			}
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(SessionNotFoundException::new);
			session.validateAnswerTime();

			Question entity = questionRepository.findQuestionByOrder(order, locale);

			QuestionResponse response = QuestionMapper.toQuestionResponse(entity, totalQuestions);
			session.startQuestionTimer();
			sessionRepository.save(session);
			response.setSessionId(session.getId());
			return response;

		} catch (DAONotFoundException e) {
			LOG.warn("No data found : {}", e.getMessage());
			throw new ResourceNotFoundException(e);
		} catch (DAOException e) {
			LOG.warn("Err getQuestion() --> {}", e);
			throw new ServiceException(e);
		} finally {
			LOG.info("END <<< getQuestionByLang");
		}
	}

	@Override
	public boolean checkValidAnswer(Integer currentQuestion, Long answerId)
			throws ServiceException, ResourceNotFoundException {
		try {
			LOG.info("START >>> checkValidAnswer");
			Integer question = questionRepository.findQuestionByAnswer(answerId);
			if ((question != null) && (question == currentQuestion)) {
				return true;
			}
			return false;
		} catch (DAONotFoundException e) {
			LOG.warn("No data found : {}", e.getMessage());
			throw new ResourceNotFoundException(e);
		} catch (DAOException e) {
			LOG.warn("Err checkValidAnswer --> {}", e);
			throw new ServiceException(e);
		} finally {
			LOG.info("END <<< checkValidAnswer");
		}
	}

	@Override
	public QuestionResponse reinitSessionAndGetFirstQuestion(UUID sessionId, Locale locale)
			throws ServiceException, SessionNotFoundException, ResourceNotFoundException,
			SessionExpiredException {
		try {
			LOG.info("START >>> reinitAndGetQuestion");
			if (sessionId == null) {
				LOG.warn("sessionId is required.");
				throw new ServiceException("sessionId is required.");
			}
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(SessionNotFoundException::new);
			sessionRepository.deleteById(session.getId());
			return getQuestion(1, null, locale);
		} finally {
			LOG.info("END <<< reinitAndGetQuestion");
		}
	}

}
