package ovh.electricowl.bubble.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import ovh.electricowl.bubble.dto.ComicResponse;
import ovh.electricowl.bubble.entities.Comic;
import ovh.electricowl.bubble.mapper.ComicMapper;
import ovh.electricowl.bubble.repositories.ComicRepository;
import ovh.electricowl.bubble.repositories.exceptions.DAOException;
import ovh.electricowl.bubble.repositories.exceptions.DAONotFoundException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;

@Service
public class ComicServiceImpl implements ComicService {

	private static final Logger LOG = LogManager.getLogger();

	private ComicRepository comicRepository;

	public ComicServiceImpl(ComicRepository comicRepository) {
		this.comicRepository = comicRepository;
	}

	@Override
	public List<ComicResponse> getAllComics() throws ResourceNotFoundException, ServiceException {
		try {
			LOG.info("START >>> getAllComics");
			List<ComicResponse> comicList = null;
			List<Comic> comicEntityList = comicRepository.findAllComics();
			comicList = comicEntityList.stream().map(ComicMapper::toDTO).toList();
			return comicList;
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
			LOG.info("END <<< getAllComics");
		}
	}

}
