package ovh.electricowl.bubble.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ovh.electricowl.bubble.entities.Comic;
import ovh.electricowl.bubble.repositories.exceptions.DAOException;

@Repository
public class ComicRepository {

	private static final Logger LOG = LogManager.getLogger(ComicRepository.class);

	@Autowired
	@Qualifier("bubbleJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public List<Comic> findComicsByProfileIds(Set<Long> ids) throws DAOException {
		try {
			LOG.info("START >>> findComicsByProfileIds");
			String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
			String sql = String.format("SELECT * FROM t_comics WHERE c_reading_profile_id IN (%s)",
					inSql);
			return jdbcTemplate.query(sql, new ComicRowMapper(), ids.toArray());
		} catch (DataAccessException e) {
			LOG.warn("Error access findComicsByProfileIds: ", e);
			throw new DAOException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			LOG.info("END <<< findComicsByProfileIds");
		}
	}

	private final class ComicRowMapper implements RowMapper<Comic> {

		@Override
		public Comic mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
			Comic data = new Comic();
			data.setId(rs.getLong("c_id"));
			data.setIsbn(rs.getString("c_isbn"));
			data.setTitle(rs.getString("c_title"));
			data.setAuthor(rs.getString("c_author"));
			data.setCountry(rs.getString("c_country"));
			data.setPublicationYear(rs.getInt("c_publication_date"));
			data.setFrontCover(rs.getString("c_front_cover"));
			data.setGenre(rs.getString("c_genre"));
			data.setEditor(rs.getString("c_editor"));
			data.setSummary(rs.getString("c_summary"));
			data.setIdProfile(rs.getLong("c_reading_profile_id"));
			return data;
		}

	}

}
