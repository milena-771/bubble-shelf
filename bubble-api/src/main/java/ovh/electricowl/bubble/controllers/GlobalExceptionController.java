package ovh.electricowl.bubble.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ovh.electricowl.bubble.repositories.exceptions.DuplicateResourceException;
import ovh.electricowl.bubble.services.exceptions.ResourceNotFoundException;
import ovh.electricowl.bubble.services.exceptions.SessionNotFoundException;

@RestControllerAdvice
public class GlobalExceptionController {

	private final Logger LOG = LogManager.getLogger(GlobalExceptionController.class);

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(
			MethodArgumentNotValidException e) {
		LOG.warn("ERR 400 Bad Request : {} --> {}", e.getClass().getSimpleName(), e);
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ ResourceNotFoundException.class, SessionNotFoundException.class })
	public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException e) {
		LOG.warn("ERR 404 Not Found : {} --> {}", e.getClass().getSimpleName(), e);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		LOG.warn("ERR 405 Method Not Allowed : {} --> {}", e.getClass().getSimpleName(), e);
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ DuplicateResourceException.class })
	public ResponseEntity<String> handleConflictException(Exception e) {
		LOG.warn("ERR 409 Conflict : {} --> {}", e.getClass().getSimpleName(), e);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception e) {
		LOG.error("ERR 500 Generic : {} --> ", e.getClass().getSimpleName(), e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Une erreur innattendue est survenue. Veuillez réessayer ultérieurement.");
	}

}
