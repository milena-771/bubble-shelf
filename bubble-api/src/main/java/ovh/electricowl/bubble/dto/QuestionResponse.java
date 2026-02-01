package ovh.electricowl.bubble.dto;

public class QuestionResponse {

	private QuestionDTO question;

	private ProgressDTO progress;

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
		return "QuestionResponse [question=" + question + ", progress=" + progress + "]";
	}
}
