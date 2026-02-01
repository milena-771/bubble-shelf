package ovh.electricowl.bubble.dto;

public class AnswerDTO {

	private Long id;

	private Integer order;

	private String label;

	private Long readingProfileId;

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

	public Long getReadingProfileId() {
		return readingProfileId;
	}

	public void setReadingProfileId(Long readingProfileId) {
		this.readingProfileId = readingProfileId;
	}

	@Override
	public String toString() {
		return "AnswersDTO [id=" + id + ", order=" + order + ", label=" + label
				+ ", readingProfileId=" + readingProfileId + "]";
	}
}
