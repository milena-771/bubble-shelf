package ovh.electricowl.bubble.repositories;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import ovh.electricowl.bubble.entities.Session;

@Repository
public class InMemorySessionRepository implements SessionRepository {

	private final Map<UUID, Session> sessions = new ConcurrentHashMap<>();

	@Override
	public Session save(Session session) {
		sessions.put(session.getId(), session);
		return session;
	}

	@Override
	public Optional<Session> findById(UUID sessionId) {
		return Optional.ofNullable(sessions.get(sessionId));
	}

	@Override
	public void deleteById(UUID sessionId) {
		sessions.remove(sessionId);
	}

	@Override
	public boolean existsById(UUID sessionId) {
		return sessions.containsKey(sessionId);
	}
}
