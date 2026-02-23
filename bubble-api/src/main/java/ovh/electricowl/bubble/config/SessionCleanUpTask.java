package ovh.electricowl.bubble.config;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ovh.electricowl.bubble.repositories.InMemorySessionRepository;

@Component
public class SessionCleanUpTask {

	private static final Logger LOG = LogManager.getLogger(SessionCleanUpTask.class);

	private static final Duration RETENTION = Duration.ofMinutes(3);

	private final InMemorySessionRepository repository;

	public SessionCleanUpTask(InMemorySessionRepository repository) {
		this.repository = repository;
	}

	@Scheduled(fixedRate = 60000)
	public void purgeExpiredSessions() {
		try {
			LOG.info("START >>> purgeExpiredSessions");
			repository.purgeExpiredSessions(RETENTION);
		} finally {
			LOG.info("END <<< purgeExpiredSessions");
		}
	}

}
