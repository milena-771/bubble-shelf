package ovh.electricowl.bubble.services;

import java.time.Clock;
import java.time.Duration;
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
	public QuestionResponse getQuestion(int order, UUID sessionId)
			throws ResourceNotFoundException, ServiceException {
		try {
			LOG.info("START >>> getQuestion");

			Session session;

			Question entity = questionRepository.findQuestionByOrder(order);

			if (order == 1) {
				session = new Session(entity.getOrder(), maxTimePerQuestion, clock);
				sessionRepository.save(session);
			} else {
				if (sessionId == null) {
					throw new ServiceException("sessionId is required for next questions");
				}
				session = sessionRepository.findById(sessionId)
						.orElseThrow(SessionNotFoundException::new);

				session.validateAnswerTime();
			}
			QuestionResponse response = QuestionMapper.toDTO(entity, totalQuestions);
			session.startQuestionTimer();
			sessionRepository.save(session);
			response.setSessionId(session.getId());
			return response;
		} catch (DAONotFoundException e) {
			LOG.warn("No data found : {}", e.getMessage());
			throw new ResourceNotFoundException(e);
		} catch (DAOException e) {
			LOG.warn("Err --> {}", e);
			throw new ServiceException(e);
		} catch (Exception e) {
			LOG.warn("Err --> {}", e);
			throw new ServiceException(e);
		} finally {
			LOG.info("END <<< getQuestion");
		}
	}

}
