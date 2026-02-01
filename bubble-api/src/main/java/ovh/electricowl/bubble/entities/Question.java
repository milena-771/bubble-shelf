package ovh.electricowl.bubble.entities;

import java.util.List;
import java.util.Objects;

public class Question {

	private Long id;

	private Integer order;

	private String label;

	private List<Answer> answers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answers, id, label, order);
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
		Question other = (Question) obj;
		return Objects.equals(answers, other.answers) && Objects.equals(id, other.id)
				&& Objects.equals(label, other.label) && Objects.equals(order, other.order);
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", order=" + order + ", label=" + label + ", answers="
				+ answers + "]";
	}
}
