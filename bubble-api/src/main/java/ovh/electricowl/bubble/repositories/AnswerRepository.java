package ovh.electricowl.bubble.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ovh.electricowl.bubble.entities.Answer;
import ovh.electricowl.bubble.repositories.exceptions.DAOException;
import ovh.electricowl.bubble.repositories.exceptions.DAONotFoundException;

@Repository
public class AnswerRepository {

	private static final Logger LOG = LogManager.getLogger(AnswerRepository.class);

	@Autowired
	@Qualifier("bubbleJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public Answer findAnswerById(Long id) throws DAONotFoundException, DAOException {
		try {
			LOG.info("START >>> findAnswerById");
			String sql = "SELECT * FROM t_answers a WHERE a.a_id = ?";
			return jdbcTemplate.queryForObject(sql, new AnswerRowMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			throw new DAONotFoundException(e);
		} catch (DataAccessException e) {
			LOG.warn("Error access findAnswerById: ", e);
			throw new DAOException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			LOG.info("END <<< findAnswerById");
		}
	}

	private final class AnswerRowMapper implements RowMapper<Answer> {

		@Override
		public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Answer data = new Answer();
			data.setId(rs.getLong("a_id"));
			data.setOrder(rs.getInt("a_order"));
			data.setLabel(rs.getString("a_label"));
			data.setReadingProfileId(rs.getLong("a_reading_profile_id"));
			return data;
		}

	}

}
