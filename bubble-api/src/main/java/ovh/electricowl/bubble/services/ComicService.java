package ovh.electricowl.bubble.services;

import java.util.List;

import ovh.electricowl.bubble.dto.ComicResponse;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;

public interface ComicService {

	public List<ComicResponse> getAllComics() throws ResourceNotFoundException, ServiceException;

}
