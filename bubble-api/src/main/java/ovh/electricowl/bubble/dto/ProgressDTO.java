package ovh.electricowl.bubble.dto;

public class ProgressDTO {

	private Integer currentQuestionNumber;

	private Integer totalQuestionNumber;

	private boolean completed;

	public Integer getCurrentQuestionNumber() {
		return currentQuestionNumber;
	}

	public void setCurrentQuestionNumber(Integer currentQuestionNumber) {
		this.currentQuestionNumber = currentQuestionNumber;
	}

	public Integer getTotalQuestionNumber() {
		return totalQuestionNumber;
	}

	public void setTotalQuestionNumber(Integer totalQuestionNumber) {
		this.totalQuestionNumber = totalQuestionNumber;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "ProgressDTO [currentQuestionNumber=" + currentQuestionNumber
				+ ", totalQuestionNumber=" + totalQuestionNumber + ", completed=" + completed + "]";
	}
}
