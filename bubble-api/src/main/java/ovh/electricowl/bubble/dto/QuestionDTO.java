package ovh.electricowl.bubble.dto;

import java.util.Objects;
import java.util.Set;

public class QuestionDTO {

	private Integer order;

	private String label;

	private Set<AnswerDTO> answers;

	@Override
	public int hashCode() {
		return Objects.hash(answers, label, order);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		QuestionDTO other = (QuestionDTO) obj;
		return Objects.equals(answers, other.answers) && Objects.equals(label, other.label)
				&& Objects.equals(order, other.order);
	}

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

	public Set<AnswerDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<AnswerDTO> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "QuestionDTO [order=" + order + ", label=" + label + ", answers=" + answers + "]";
	}
}
