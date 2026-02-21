package ovh.electricowl.bubble.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ovh.electricowl.bubble.dto.ComicDTO;
import ovh.electricowl.bubble.dto.ProfileDTO;
import ovh.electricowl.bubble.dto.ResultResponse;
import ovh.electricowl.bubble.entities.Answer;
import ovh.electricowl.bubble.entities.Comic;
import ovh.electricowl.bubble.entities.Profile;
import ovh.electricowl.bubble.entities.Session;
import ovh.electricowl.bubble.mapper.ComicMapper;
import ovh.electricowl.bubble.mapper.ReadingProfileMapper;
import ovh.electricowl.bubble.repositories.AnswerRepository;
import ovh.electricowl.bubble.repositories.ComicRepository;
import ovh.electricowl.bubble.repositories.ReadingProfileRepository;
import ovh.electricowl.bubble.repositories.SessionRepository;
import ovh.electricowl.bubble.repositories.exceptions.DAOException;
import ovh.electricowl.bubble.repositories.exceptions.DAONotFoundException;
import ovh.electricowl.bubble.services.exceptions.InvalidAnswerException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;
import ovh.electricowl.bubble.services.exceptions.TestNotCompletedException;

@Service
public class ResultServiceImpl implements ResultService {

	private static final Logger LOG = LogManager.getLogger(ResultServiceImpl.class);

	@Value("${bubble.total.questions.number}")
	private Integer totalQuestions;

	private final AnswerRepository answerRepository;

	private SessionRepository sessionRepository;

	private ReadingProfileRepository profileRepository;

	private ComicRepository comicRepository;

	public ResultServiceImpl(AnswerRepository answerRepository, SessionRepository sessionRepository,
			ReadingProfileRepository profileRepository, ComicRepository comicRepository) {
		this.answerRepository = answerRepository;
		this.sessionRepository = sessionRepository;
		this.profileRepository = profileRepository;
		this.comicRepository = comicRepository;
	}

	@Override
	public ResultResponse retrieveResults(UUID sessionId)
			throws ServiceException, SessionNotFoundException, TestNotCompletedException,
			InvalidAnswerException, ResourceNotFoundException {
		if (sessionId == null) {
			throw new ServiceException("sessionId is required.");
		}
		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(SessionNotFoundException::new);
		if (!session.isCompleted()) {
			LOG.error("Test is not completed.");
			throw new TestNotCompletedException("Test is not completed.");
		}
		Map<Integer, Long> answers = session.getAnswers();
		Set<Long> idAnswers = answers.values().stream().collect(Collectors.toSet());
		if (idAnswers.size() != totalQuestions) {
			throw new InvalidAnswerException("Number of answer is incorrect.");
		}
		Map<Long, Integer> statistics = calculateStatistics(idAnswers);
		Set<ProfileDTO> profiles = generateProfileDescription(statistics);
		Set<Long> idProfiles = profiles.stream().map(p -> p.getId()).collect(Collectors.toSet());
		Set<ComicDTO> comics = getRecommendComics(idProfiles);

		session.startQuestionTimer();
		sessionRepository.save(session);

		ResultResponse results = new ResultResponse();
		results.setStatistics(statistics);
		results.setProfileDescription(profiles);
		results.setRecommendedComics(comics);
		results.setSessionId(session.getId());
		return results;
	}

	private Map<Long, Integer> calculateStatistics(Set<Long> idAnswers)
			throws ServiceException, ResourceNotFoundException {
		try {
			LOG.info("START >>> calculateStatistics");
			Map<Long, Integer> results = new HashMap<Long, Integer>();
			for (Long id : idAnswers) {
				Answer answer = answerRepository.findAnswerById(id);
				Long idProfile = answer.getReadingProfileId();
				int nbProfile = results.getOrDefault(idProfile, 0);
				results.put(idProfile, ++nbProfile);
			}
			return results;
		} catch (DAONotFoundException e) {
			LOG.error("Answer not found");
			throw new ResourceNotFoundException("Answer not found to calculate result", e);
		} catch (DAOException e) {
			LOG.error("Technical error to calculate result");
			throw new ServiceException("Error to calculate result");
		} finally {
			LOG.info("END <<< calculateStatistics");
		}
	}

	private Set<ProfileDTO> generateProfileDescription(Map<Long, Integer> results)
			throws ServiceException {
		try {
			LOG.info("START >>> generateProfileDescription");
			Integer maxValue = results.entrySet().stream()
					.max((r1, r2) -> r1.getValue().compareTo(r2.getValue())).get().getValue();
			Set<Long> idProfiles = results.entrySet().stream()
					.filter(r -> (r != null) && (r.getValue() == maxValue)).map(r -> r.getKey())
					.collect(Collectors.toSet());
			List<Profile> profiles = profileRepository.findAlProfilesByIds(idProfiles);
			return profiles.stream().filter(p -> p != null)
					.map(p -> ReadingProfileMapper.toProfileDTO(p)).collect(Collectors.toSet());
		} catch (DAOException e) {
			LOG.error("Technical error to generate profile");
			throw new ServiceException("Error to generate profile");
		} finally {
			LOG.info("END <<< generateProfileDescription");
		}
	}

	private Set<ComicDTO> getRecommendComics(Set<Long> idProfiles) throws ServiceException {
		try {
			LOG.info("START >>> getRecommendComics");
			List<Comic> comics = comicRepository.findComicsByProfileIds(idProfiles);
			return comics.stream().map(ComicMapper::toComicDTO).collect(Collectors.toSet());
		} catch (DAOException e) {
			LOG.error("Technical error to get recommended comics");
			throw new ServiceException("Error to get recommended comics");
		} finally {
			LOG.info("END <<< getRecommendComics");
		}
	}

}
