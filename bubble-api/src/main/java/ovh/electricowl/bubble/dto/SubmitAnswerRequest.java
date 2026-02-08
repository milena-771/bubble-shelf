package ovh.electricowl.bubble.dto;

import jakarta.validation.constraints.NotNull;

public class SubmitAnswerRequest {

	@NotNull
	private Integer questionOrder;

	@NotNull
	private Long answerId;

	public Integer getQuestionOrder() {
		return questionOrder;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	@Override
	public String toString() {
		return "SubmitAnswerRequest [questionOrder=" + questionOrder + ", answerId=" + answerId
				+ "]";
	}
}
