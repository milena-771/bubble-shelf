package ovh.electricowl.bubble.dto;

import java.util.List;

public class QuestionDTO {

	private Integer order;

	private String label;

	private List<AnswerDTO> answers;

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<AnswerDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "QuestionDTO [order=" + order + ", label=" + label + ", answers=" + answers + "]";
	}

}
