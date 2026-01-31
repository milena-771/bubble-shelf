package ovh.electricowl.bubble.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ovh.electricowl.bubble.entities.Comic;
import ovh.electricowl.bubble.repositories.exceptions.DAOException;
import ovh.electricowl.bubble.repositories.exceptions.DAONotFoundException;

@Repository
public class ComicRepository {

	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	@Qualifier("bubbleJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public List<Comic> findAllComics() throws DAOException, DAONotFoundException {
		try {
			LOG.info("START >>> findAllComics");
			String sql = "SELECT * FROM t_comics";
			return jdbcTemplate.query(sql, new ComicRowMapper());
		} catch (EmptyResultDataAccessException e) {
			LOG.error("Error resources not found findAllComics: {}", e);
			throw new DAONotFoundException(e);
		} catch (DataAccessException e) {
			LOG.error("Error data access findAllComics: {}", e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOG.error("Error findAllComics: {}", e);
			throw e;
		} finally {
			LOG.info("END <<< findAllComics");
		}
	}

	private final class ComicRowMapper implements RowMapper<Comic> {

		@Override
		public Comic mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
			Comic data = new Comic();
			data.setId(rs.getLong("id"));
			data.setIsbn(rs.getString("isbn"));
			data.setTitle(rs.getString("title"));
			data.setAuthor(rs.getString("author"));
			data.setCountry(rs.getString("country"));
			data.setPublicationYear(rs.getInt("publication_date"));
			data.setFrontCover(rs.getString("front_cover"));
			data.setGenre(rs.getString("genre"));
			data.setEditor(rs.getString("editor"));
			data.setSummary(rs.getString("summary"));
			return data;
		}

	}

}
