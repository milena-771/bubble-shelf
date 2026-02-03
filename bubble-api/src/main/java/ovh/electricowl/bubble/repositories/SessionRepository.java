package ovh.electricowl.bubble.repositories;

import java.util.Optional;
import java.util.UUID;

import ovh.electricowl.bubble.entities.Session;

public interface SessionRepository {

	Session save(Session session);

	Optional<Session> findById(UUID sessionId);

	void deleteById(UUID sessionId);

	boolean existsById(UUID sessionId);
}
