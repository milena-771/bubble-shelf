package ovh.electricowl.bubble.entities;

public class Answer {

	private Long id;

	private Integer order;

	private String label;

	private String language;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getReadingProfileId() {
		return readingProfileId;
	}

	public void setReadingProfileId(Long readingProfileId) {
		this.readingProfileId = readingProfileId;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", order=" + order + ", label=" + label + ", language="
				+ language + ", readingProfileId=" + readingProfileId + "]";
	}
}
