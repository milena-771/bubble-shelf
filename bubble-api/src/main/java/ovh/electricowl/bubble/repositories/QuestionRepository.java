package ovh.electricowl.bubble.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import ovh.electricowl.bubble.entities.Answer;
import ovh.electricowl.bubble.entities.Question;
import ovh.electricowl.bubble.repositories.exceptions.DAOException;
import ovh.electricowl.bubble.repositories.exceptions.DAONotFoundException;

@Repository
public class QuestionRepository {

	private static final Logger LOG = LogManager.getLogger(QuestionRepository.class);

	@Autowired
	@Qualifier("bubbleJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public Question findQuestionByOrder(int order, Locale local)
			throws DAONotFoundException, DAOException {
		try {
			LOG.info("START >>> findQuestionById");
			String sql = """
					SELECT q_id, q_order, qt_language, qt_label, a_id, a_order, a_reading_profile_id, at_language, at_label FROM t_questions q
					JOIN t_questions_translations qt ON  (qt.qt_question_id = q.q_id AND qt.qt_language = ?)
					JOIN t_answers a ON a.a_question_id = q.q_id
					JOIN t_answers_translations at ON (at.at_answer_id = a.a_id AND at.at_language = ?)
					WHERE q.q_order = ?
					""";
			return jdbcTemplate.query(sql, new QuestionWithAnswersExtractor(), local.getLanguage(),
					local.getLanguage(), order);
		} catch (EmptyResultDataAccessException e) {
			LOG.error("Error resources not found findQuestionByOrder: {}", e);
			throw new DAONotFoundException(e);
		} catch (DataAccessException e) {
			LOG.error("Error data access findQuestionByOrder: {}", e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOG.error("Error findQuestionByOrder: {}", e);
			throw new DAOException(e);
		} finally {
			LOG.info("END <<< findQuestionById");
		}
	}

	public Integer findQuestionByAnswer(Long answerId) throws DAONotFoundException, DAOException {
		try {
			LOG.info("START >>> findQuestionByAnswer");
			String sql = "SELECT q_order FROM t_questions q JOIN t_answers a ON a.a_question_id=q.q_id WHERE a_id = ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, answerId);
		} catch (EmptyResultDataAccessException e) {
			LOG.error("Error resources not found findAllComics: {}", e);
			throw new DAONotFoundException(e);
		} catch (DataAccessException e) {
			LOG.error("Error data access findQuestionByOrder: {}", e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOG.error("Error findQuestionByOrder: {}", e);
			throw new DAOException(e);
		} finally {
			LOG.info("END <<< findQuestionByAnswer");
		}

	}

	public class QuestionWithAnswersExtractor implements ResultSetExtractor<Question> {

		@Override
		public Question extractData(ResultSet rs) throws SQLException, DataAccessException {

			Question question = null;
			List<Answer> answers = new ArrayList<>();

			while (rs.next()) {
				Answer answer = new Answer();
				answer.setId(rs.getLong("a_id"));
				answer.setOrder(rs.getInt("a_order"));
				answer.setLabel(rs.getString("at_label"));
				answer.setLanguage(rs.getString("at_language"));
				answer.setReadingProfileId(rs.getLong("a_reading_profile_id"));
				answers.add(answer);

				// Création unique de la question
				if (question == null) {
					question = new Question();
					question.setId(rs.getLong("q_id"));
					question.setOrder(rs.getInt("q_order"));
					question.setLanguage(rs.getString("qt_language"));
					question.setLabel(rs.getString("qt_label"));
					question.setAnswers(answers);
				}
			}
			return question;
		}

	}

}
