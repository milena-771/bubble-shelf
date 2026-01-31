package ovh.electricowl.bubble.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ovh.electricowl.bubble.dto.ComicResponse;
import ovh.electricowl.bubble.services.ComicService;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.ServiceException;

@RestController
@RequestMapping("/comics")
public class ComicController {

	private static final Logger LOG = LogManager.getLogger(ComicController.class);

	@Autowired
	private ComicService comicService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ComicResponse> getAllComics() throws ResourceNotFoundException, ServiceException {
		return comicService.getAllComics();
	}

	/*
	 * public ResponseEntity<List<ComicResponse>> getAllComics(){
	 * List<ComicResponse> comics = comicService.getAllComics(); return
	 * ResponseEntity.status(HttpStatus.OK).body(comics); }
	 */
}
