package ovh.electricowl.bubble.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public Question findQuestionByOrder(int order) throws DAONotFoundException, DAOException {
		try {
			LOG.info("START >>> findQuestionById");
			String sql = "SELECT * FROM t_questions q JOIN t_answers a ON a.a_question_id=q.q_id WHERE q.q_order = ?";
			return jdbcTemplate.query(sql, new QuestionWithAnswersExtractor(), order);
		} catch (EmptyResultDataAccessException e) {
			LOG.error("Error resources not found findAllComics: {}", e);
			throw new DAONotFoundException(e);
		} catch (DataAccessException e) {
			LOG.error("Error data access findQuestionByOrder: {}", e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOG.error("Error findQuestionByOrder: {}", e);
			throw e;
		} finally {
			LOG.info("END <<< findQuestionById");
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
				answer.setLabel(rs.getString("a_label"));
				answer.setReadingProfileId(rs.getLong("a_reading_profile_id"));
				answers.add(answer);

				// Création unique de la question
				if (question == null) {
					question = new Question();
					question.setId(rs.getLong("q_id"));
					question.setOrder(rs.getInt("q_order"));
					question.setLabel(rs.getString("q_label"));
					question.setAnswers(answers);
				}
			}
			return question;
		}

	}

}
