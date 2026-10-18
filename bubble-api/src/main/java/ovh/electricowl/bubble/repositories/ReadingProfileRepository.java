package ovh.electricowl.bubble.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ovh.electricowl.bubble.entities.Profile;
import ovh.electricowl.bubble.repositories.exceptions.DAOException;

@Repository
public class ReadingProfileRepository {

	private static final Logger LOG = LogManager.getLogger(ReadingProfileRepository.class);

	@Autowired
	@Qualifier("bubbleJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public List<Profile> findAlProfilesByIds(Set<Long> ids, Locale locale) throws DAOException {
		try {
			LOG.info("START >>> findAlProfilesByIds");
			String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
			String sql = String.format(
					"SELECT rp_id, rp_profile_name, rpt_description, rpt_language FROM t_reading_profiles rp "
							+ "JOIN t_reading_profiles_translations rpt ON rpt.rpt_reading_profile_id = rp.rp_id "
							+ "WHERE rp_id IN (%s)" + "AND rpt.rpt_language = ? ",
					inSql);

			List<Object> params = new ArrayList<>(ids);
			params.add(locale.getLanguage().toString());

			return jdbcTemplate.query(sql, new ReadingProfileRowMapper(), params.toArray());
		} catch (DataAccessException e) {
			LOG.warn("Error access findAlProfilesByIds: ", e);
			throw new DAOException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			LOG.info("END <<< findAlProfilesByIds");
		}
	}

	private final class ReadingProfileRowMapper implements RowMapper<Profile> {

		@Override
		public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
			Profile data = new Profile();
			data.setId(rs.getLong("rp_id"));
			data.setName(rs.getString("rp_profile_name"));
			data.setDescription(rs.getString("rpt_description"));
			data.setLanguage(rs.getString("rpt_language"));
			return data;
		}

	}

}
