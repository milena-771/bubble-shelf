package ovh.electricowl.bubble.dto;

import java.util.UUID;

public class QuestionResponse {

	private UUID sessionId;

	private Long selectedAnswer;

	private QuestionDTO question;

	private ProgressDTO progress;

	public UUID getSessionId() {
		return sessionId;
	}

	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}

	public Long getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Long selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public QuestionDTO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}

	public ProgressDTO getProgress() {
		return progress;
	}

	public void setProgress(ProgressDTO progress) {
		this.progress = progress;
	}

	@Override
	public String toString() {
		return "QuestionResponse [sessionId=" + sessionId + ", selectedAnswer=" + selectedAnswer
				+ ", question=" + question + ", progress=" + progress + "]";
	}
}
