package ovh.electricowl.bubble.entities;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ovh.electricowl.bubble.services.exceptions.SessionExpiredException;
import ovh.electricowl.bubble.services.exceptions.TestAlreadyCompletedException;

public class Session {

	private static final Logger LOG = LogManager.getLogger(Session.class);

	private UUID id;

	private int currentQuestionNumber;

	private final Map<Integer, Long> answers = new HashMap<>();

	private boolean completed;

	private Instant expiredAt;

	private Instant lastQuestionDisplayedAt;

	private Duration maxTimePerQuestion;

	private Clock clock;

	public Session(int questionOrder, Duration maxTimePerQuestion, Clock clock) {
		this.id = UUID.randomUUID();
		this.currentQuestionNumber = questionOrder;
		this.maxTimePerQuestion = maxTimePerQuestion;
		this.clock = clock;
		this.completed = false;
		startQuestionTimer();
	}

	public UUID getId() {
		return id;
	}

	public int getCurrentQuestionNumber() {
		return currentQuestionNumber;
	}

	public Map<Integer, Long> getAnswers() {
		return Collections.unmodifiableMap(answers);
	}

	public Instant getLastQuestionDisplayedAt() {
		return lastQuestionDisplayedAt;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void startQuestionTimer() {
		this.lastQuestionDisplayedAt = Instant.now(clock);
	}

	public void validateAnswerTime() throws SessionExpiredException {
		if (lastQuestionDisplayedAt == null) {
			return;
		}

		Duration elapsed = Duration.between(lastQuestionDisplayedAt, Instant.now(clock));

		if (elapsed.compareTo(maxTimePerQuestion) > 0) {

			// mémorise le moment exact d'expiration (une seule fois)
			if (expiredAt == null) {
				expiredAt = Instant.now(clock);
				LOG.warn("Session expired.");
			}
			throw new SessionExpiredException("Session expired, restart the test.");
		}
	}

	public boolean shouldBeDeleted(Duration retentionAfterExpiration) {
		if (expiredAt == null) {
			return false;
		}

		return Instant.now(clock).isAfter(expiredAt.plus(retentionAfterExpiration));
	}

	public void addAnswer(Integer questionId, Long answerId) throws TestAlreadyCompletedException {
		if (completed) {
			LOG.warn("Test already completed.");
			throw new TestAlreadyCompletedException("Test already completed.");
		}
		answers.put(questionId, answerId);
	}

	public void moveToNextQuestion(int nextQuestionNumber) {
		this.currentQuestionNumber = nextQuestionNumber;
		startQuestionTimer();
	}

	public void markCompleted() {
		this.completed = true;
	}

}
