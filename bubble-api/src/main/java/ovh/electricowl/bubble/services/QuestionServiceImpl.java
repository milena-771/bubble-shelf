package ovh.electricowl.bubble.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ovh.electricowl.bubble.dto.QuestionResponse;
import ovh.electricowl.bubble.entities.Question;
import ovh.electricowl.bubble.mapper.QuestionMapper;
import ovh.electricowl.bubble.repositories.QuestionRepository;
import ovh.electricowl.bubble.repositories.exceptions.DAOException;
import ovh.electricowl.bubble.repositories.exceptions.DAONotFoundException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final Logger LOG = LogManager.getLogger(QuestionServiceImpl.class);

	@Value("${bubble.total.questions.number}")
	private Integer totalQuestions;

	private QuestionRepository questionRepository;

	public QuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	@Override
	public QuestionResponse getQuestion(int order)
			throws ResourceNotFoundException, ServiceException {
		try {
			LOG.info("START >>> getQuestion");
			Question entity = questionRepository.findQuestionByOrder(order);
			QuestionResponse response = QuestionMapper.toDTO(entity, totalQuestions);
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
