package ovh.electricowl.bubble.dto;

public class ProgressDTO {

	private Integer currentQuestionNumber;

	private Integer totalQuestionNumber;

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

	@Override
	public String toString() {
		return "ProgressDTO [currentQuestionNumber=" + currentQuestionNumber
				+ ", totalQuestionNumber=" + totalQuestionNumber + "]";
	}

}
